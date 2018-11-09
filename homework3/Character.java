/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Character Class --  This class will construct a character (player or NPC)
 *  			 and store then them in a arraylist. There are two constructors for this class.
 *  			 First one takes in a Scanner, double, and int to read the characters from the
 *  			 data file. Second one takes in int, String, String and int (ID, name, desc, placeID)
 *  			 to create characters if the user wants more than the characters present in the gdf file.
 *  			 This class also now has play capabilities of the play() method in Game class.
 *  			 Some key methods are:
 *  			 --> getCharacterbyID: takes in an int value and returns the Character associated with
 *  				 ID. If no character can be found returns null.
 *  			 --> makeMove(): encapsulates the capablility of the play() method from game class. Determines
 *  				  if the character is of type Player or of type Character and calls appropriate desicionmaker
 *  				  for it to get the move. It will subpress some of the moves of the NPC while still showing 
 *  				  moves like get, drop, and use.
 *  			 --> display(): prints out the description of the character
 *  			 --> getArtifact(): returns a random artifact from the character's inventory.
 *  			 --> exit(): Updates the Collection of Characters in Character class and Game class.
 *  				  If a player character exits this will remove it from the both classes and
 *	 				   update the internal counter to determine when to exit the game, that is,
 *					   if no player characters are present the game will stop playing even if there
 *					   are NPC present in the game.
 */




import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Character {

	private int ID;
	private String name;
	private String description;
	private String type;
	private Place charLoc;
	public static int numAdded;
	private int hintCount;
	private static int playable = 0;

	private boolean canRepeat = false;

	private ArrayList<Artifact> char_artf = new ArrayList<Artifact>();
	private static ArrayList<Character> character = new ArrayList<Character>();
	
	public Character(Scanner char_scn, double version, int placeID){
		
		if(version < 4) {
			return;
		}

		Random rand = new Random();
		
		/* This will add read the line(s) to extract the information for the Character
		 * the format is similar to one provided in the GDF format file. The first value
		 * is ID of the Character, second is the name and then number of description lines
		 * followed by description itself. The string is split using spaces and read accordingly.
		 */
		String next = CleanLineScanner.getCleanLine(char_scn);
		if(next.equals("\0") || next.equals(null)) {
			//return
		} else {

			String[] data = next.split(" ", -1);
			this.ID = Integer.parseInt(data[0]);
			
			//get name
			for(int i = 1; i < data.length; i++) {
				if(i == 1) {
					this.name = data[i];
				} else {
					this.name += data[i];
				}
				this.name += " ";
			}
			
			this.name = this.name.trim();
			this.description = CleanLineScanner.getDescription(char_scn);
			//this.print();

			
			//if placeID for the character is 0 assign it to a random place
			if(placeID == 0){
				int random = rand.nextInt(Place.place.size() - 2) + 1;
				charLoc = Place.place.get(random);
				charLoc.addCharacter(this);
			} else {
			  //if placeID is greater than 0 assign it to the place with that placeID

				this.charLoc = Place.getPlaceByID(placeID);
				charLoc.addCharacter(this);
			}

			//increament the counter for number of user players
			if(this instanceof Player){
				playable++;
			}

			this.hintCount = 3;					//gives all the players 3 hints when initialized
			character.add(this);
			
			numAdded++;
		}
	}
	
	
	//constructor to create characters taken from the user if they need more
	public Character(int id, String name, String desc, int placeID) {
		
		this.ID = id;
		this.name = name;
		this.description = desc;
		this.charLoc = Place.getPlaceByID(placeID);

		playable++;
		this.hintCount = 3;
		character.add(this);
		
		Game.charcUpdate(this, 1);
	}
	
	
	/* takes in an int value and returns the Character associated with
	 * ID. If no character can be found returns null.
	 */
	public static Character getCharacterByID(int id) {
		
		for(int i = 0; i < character.size(); i++) {
			if(character.get(i).ID == Math.abs(id)) {
				return character.get(i);
			}
		}	
		return null;
	}
	
	
	
	//adds artifact to the arraylist
	public void addUsrArtf(Artifact a) {
		char_artf.add(a);
	}
	
	/* Returns the current place of the characterso that it can be used in 
	 * other classes like Artifact and Place to perform functions like use(),
	 * pickArtifact etc.
	 */
	public static Place getCurrentPlace(Character c) {
		return c.charLoc;
	}


	/* Function to get a random artifact name in the current character's possession
	 * in that index position in the Artifact arraylist of the characters.
	 * Used by NPC decision maker to get a random artifact from current character's
	 * inventory.
	 */
	public String getArtifact(){
		
		if(this.char_artf.size() == 0) {
			return " ";
		}

		Collections.shuffle(char_artf);

		return this.char_artf.get(0).name();
	}


	/* Incapsulates the capablility of the play() method from game class. Determines
	 * if the character is of type Player or of type Character and calls appropriate desicionmaker
	 * for it to get the move. It will subpress some of the moves of the NPC while still showing 
	 * moves like get, drop, and use.
	 */
	public void makeMove() {

		if(charLoc.name().equalsIgnoreCase("Exit")) {
			System.out.println("\n" + charLoc.description());
			System.out.println("\t~ " + this.name + "~  has left the game");
			this.exit();
			//System.out.println("\nYou are now leaving the game..." + "\nGoodbye :)\n\n");
			//System.exit(0);
		} else if (charLoc.name().equalsIgnoreCase("Nowhere")) {
			System.out.println("\n" + charLoc.description());
			this.exit();
			System.out.println("\t~ " + this.name + "~  has left the game");
			//System.out.println("\nYou are now leaving the game..." + "\nGoodbye :)\n\n");
			System.exit(0);
		} else {
			//System.out.println("\n" + charLoc.description());
			if(this instanceof Player){
				this.print();
				this.charLoc.description();
			}
		}

		Move.MoveType mv = Move.MoveType.STAY;
		String arg = "";

		if(this instanceof Player){

			System.out.print(">>>Enter Where would you like to go: ");
			UserDecision ui = new UserDecision();
			Move m = ui.getMove(this, charLoc);

			mv = m.getType();
			arg = m.args();

			//System.out.println("IS PLAYER!!");
		} else if(this instanceof NPC){

			ArtificialDecision ai = new ArtificialDecision();
			Move m = ai.getMove(this, charLoc);

			mv = m.getType();
			arg = m.args();

			//System.out.println("IS NPC");
		}


		switch(mv) {

			case GO:
				charLoc.removeCharacter(this);
				Place tempCurr = charLoc;

				if(this instanceof NPC && 
							(!charLoc.followDirection(arg).name().equalsIgnoreCase("EXIT")
							 && !charLoc.followDirection(arg).name().equalsIgnoreCase("NOWHERE"))){
					tempCurr = this.charLoc.followDirection(arg);
				} else {
					tempCurr = this.charLoc.followDirection(arg);
				}

				if(tempCurr == charLoc && this instanceof Player){
					//this case only happens when user input direction does not exist in this room
					System.out.println("\nThere was either no Room in that direction or\n"
										+ "You entered wrong input check and Try Again.\n");
				} else {
					charLoc = tempCurr;
				}

				this.charLoc.addCharacter(this);
				this.canRepeat = false;
				break;
			
			case LOOK:
				System.out.println(this.charLoc.description());
				this.charLoc.lookArtifact();
				this.canRepeat = true;
				break;
			
			case DISPLAY:

				if(this.hintCount == 0) {
					System.out.println("\n\nYou have used up all your hints.\nFrom here on out you must procede with caution");
					break;
				}
				
				this.hintCount--;
				this.print();
				this.charLoc.display();
				for(int i = 0; i < this.char_artf.size(); i++) {
					System.out.println("\nName: " + this.char_artf.get(i).name()
									+ "\nValue: " + this.char_artf.get(i).value()
									+ "\tMobility: " + this.char_artf.get(i).size()
									+ "\nDescription: " + this.char_artf.get(i).description());
				}

				this.canRepeat = true;
				break;
			case GET:
				Artifact pickup = charLoc.pickArtifact(arg, this);
				this.char_artf.add(pickup);
				canRepeat = false;
				break;
			
			case DROP:
				if(this.char_artf.size() == 0 && this instanceof Player) {
					System.out.println("\nYou don't have anything in your possession\n");
					break;
				} else if (this.char_artf.size() == 0) {
					break;
				}
				
				//loops to find the artifact in the inventory and if found it will
				//add it to the places collection of artifacts and remove it from user's
				for(int i = 0; i < this.char_artf.size(); i++) {
					if(this.char_artf.get(i).name().equalsIgnoreCase(arg)) {
						charLoc.addArtifact(this.char_artf.get(i));	 			//adds the artifact to the room
						System.out.println("\nDroped " + arg + " in " + charLoc.name());
						this.char_artf.remove(i);						//removes the artifact from user inventory
						break;
					} else if (i == this.char_artf.size() - 1 && this instanceof Player) {
						System.out.println("\nYou Don't have any artifact called " + arg);
					}
				}
				this.canRepeat = false;
				break;
			
			case USE:
				if(this.char_artf.size() == 0 && this instanceof Player) {
					System.out.println("\nYou don't have anything in your possession\n");
				} else if (this.char_artf.size() == 0) {
					break;
				}
				
				//loops to find the artifact in user inventory and if found it will
				//call the use() function to use the artifact in the room user is in
				for(int i = 0; i < this.char_artf.size(); i++) {
					if(this.char_artf.get(i).name().equalsIgnoreCase(arg)) {
						this.char_artf.get(i).use(this);
						break;
					} else if (i == this.char_artf.size() -1 && this instanceof Player) {
						System.out.println("\nYou Don't have any artifact called " + arg);
					}
				}
				this.canRepeat = false;
				break;

			case INVE: case INVENTORY:
				for(int i = 0; i < this.char_artf.size(); i++) {
					System.out.println("\nName: " + this.char_artf.get(i).name()
									+ "\nValue: " + this.char_artf.get(i).value()
									+ "\tMobility: " + this.char_artf.get(i).size()
									+ "\nDescription: " + this.char_artf.get(i).description());
				}

				this.canRepeat = true;
				break;

			case EXIT: case QUIT:

				if(arg.equalsIgnoreCase("All")){
					System.out.println("\nExiting Game...\nGoodbye :-)\n\n\t~ All players left\n\n");
					System.exit(0);
				}

				System.out.println("\nGoodbye " + this.name);
				System.out.println("\t~ " + this.name + "~  has left the game");

				this.exit();
				System.out.println("\nThere are still " + playable + " players in the game");
				this.canRepeat = false;
				break;
				//System.exit(0);
			
			default:
				System.out.println("\nYou couldn't do anything. Maybe try doing some else...\n");
				this.canRepeat = true;
			}

	}

	//determines if the player can take another turn after a command likw
	//look, and inventory since it's just checking they get another other
	public boolean nextTurn() {
		return this.canRepeat;
	}

	/* Updates the Collection of Characters in Character class and Game class.
	 * If a player character exits this will remove it from the both classes and
	 * update the internal counter to determine when to exit the game, that is,
	 * if no player characters are present the game will stop playing even if there
	 * are NPC present in the game.
	 */
	public void exit() {
		
		if(this instanceof Player && playable > 0) {
			playable--;
			Game.charcUpdate(this, 0);
		}

		if(playable == 0){
			System.out.println("All players have exited the game...\n\n");
			System.exit(0);
		}

		charLoc.removeCharacter(this);
		character.remove(this);
	}
	
	
	public void print() {
		System.out.println("\nCharacter Name: " + this.name
						   + "\nCurrent Location: " + this.charLoc.name() + "\n");
	}

	public void display() {
		System.out.println("Description" + this.description + "\n");
	}
	
	
}

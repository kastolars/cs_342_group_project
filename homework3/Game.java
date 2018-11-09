/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Game Class -- Runs for infinite time asking swtiching between characters
 *  			 and calling makeMove on them. This class for this project is the starting point
 *  			 which will delegate things to the other classes including asking and making moves 
 *  			 that the user will provide. This no longer constains a arraylit of user artifacts
 *  			 since each character will have it's on collection now. This class also no longer
 *  			 has getCurrPlace() since each character will have it's own currPlace. Game will now
 *  			 have static collection of all characters so it can cycle through them and make moves
 *  			 based on User or AI moves. Game has one new function:
 *  			   --> charUpdate(): Removes character when from the personal list when a character 
 *					    (player or NPC) exits the game.
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	private String name;
	private static Place Curr;		//Current Place the user is at
	private int numPlaces;			//num of Places, Directions, and Artifacts
	private int numDir;				//   to be read from the GDF file
	private int numArtf;
	private int numChar;
	private double verNum;				//version number for the GDF file
	//private ArrayList<Artifact> usr_artf = new ArrayList<Artifact>();	
												//stores all the places in game
	
	private static ArrayList<Character> charc = new ArrayList<Character>();
	
	public Game(Scanner game_scn) {
		
		//String next = game_scn.nextLine();
		
		String next = CleanLineScanner.getCleanLine(game_scn);
		String temp = next.substring(4,6);
		
		this.verNum = Double.parseDouble(temp);
		//System.out.println(verNum);
		
		this.name = next.substring(8);			//name of the game --> skips GDF #		
		//this.name = next;						//name of the game
		
		int type = 0;							//used for switch case to determine
												//if place, direction, artifact or none
		
		/* Loops until there are lines in the file that can be read. It will divide the
		 * the different sections of the file into PLACES, DIRECTIONS, and ARTIFACTS by
		 * looking for those words in each line. Once found it will go through the switch
		 * case and call constructor for that object until it has created an constructor
		 * for all the of that object type. This is done by keeping track of how many were
		 * created and how many are there in file. Each class has a public static counter
		 * that counts how many objects were created.
		 */
		while(game_scn.hasNextLine()) {
			
			next = CleanLineScanner.getCleanLine(game_scn);
			if(next.equals(null)) {
				type = 0;
				//return to loop
			} else if(next.startsWith("PLACES")) {
				type = 1;
				numPlaces = Integer.parseInt(next.substring(7));		//gets the # of places
				
			} else if(next.startsWith("DIRECTION")) {
				type = 2;
				numDir = Integer.parseInt(next.substring(11));
				
			} else if(next.startsWith("ARTIFACTS")) {
				type = 3;
				numArtf = Integer.parseInt(next.substring(10));
			} else if(next.startsWith("CHARACTERS") && verNum >= 4){
				type = 4;
				numChar = Integer.parseInt(next.substring(11));
			}
			
			
			switch(type) {
			
			case 1:
				next = game_scn.nextLine();
				//account for the 2 extra rooms -- exit and nowhere
				while(game_scn.hasNextLine() && Place.numAdded < numPlaces+2) {
					Place p_add = new Place(game_scn, verNum);
				}
				type = 0;
				break;
			case 2:
				next = game_scn.nextLine();
				while(game_scn.hasNextLine() && Direction.numAdded < numDir) {
					Direction d_add = new Direction(game_scn, verNum);
				}
				type = 0;
				break;
			case 3:
				next = game_scn.nextLine();
				while(game_scn.hasNextLine() && Artifact.numAdded < numArtf) {
					Artifact a_add = new Artifact(game_scn, verNum);
				}
				type = 0;
				break;
			case 4:
				next = game_scn.nextLine();
				while(game_scn.hasNextLine() && Character.numAdded < numChar) {
					
					String s = CleanLineScanner.getCleanLine(game_scn);
					if(s.startsWith("PLAYER") || s.startsWith("NPC")) {
						String[] data = s.split(" ", -1);
						String char_type = data[0];
						int charLoc = Integer.parseInt(data[1]);
						
						if(char_type.equalsIgnoreCase("Player")){
							Player p = new Player(game_scn, verNum, charLoc);
							charc.add(p);
						} else if (char_type.equalsIgnoreCase("NPC")) {
							NPC n = new NPC(game_scn, verNum, charLoc);
							charc.add(n);
						}
					}
					
					//Character c_add = new Character(game_scn, verNum);
				}
				type = 0;
				break;
			default:
				break;
				//return to the loop
			}
			
		}
		
	}


	public int numCharacter() {
		return charc.size();
	}
	
	
	/* Returns the current place so that it can be used in other classes like
	 * Artifact and Place to perform functions like use(), pickArtifact etc.
	 */
	/*public static Place getCurrentPlace() {
		return Curr;
	}*/
	
	/*public void addPlace(Place p) {
		place.add(p);			//adds the place into ArrayList
		
	}*/
	
	//prints for debug purpose
	public void print() {
		System.out.println("Game Name: " + name + "\n" +
						   "Current Room: " + Curr.name() + "\n" +
						   "Describtion: " + Curr.description());
		
	}
	
	/* Removes character when from the personal list when a character (player or NPC)
	 * exits the game.
	 */
	public static void charcUpdate(Character c, int flag) {
		
		if(flag == 0){
			charc.remove(c);
		} else if(flag == 1){
			charc.add(c);
		}

		//charc.remove(c);
	}
	
	/* Starts the Game:
	 *  --> User can input direction they want to move in
	 *  --> Look in the room for Artifacts
	 *  		NOTE: Without trying LOOK the user won't be able to see the
	 *  			  Artifacts in the room
	 *  --> GET: user can try to pickup an artifact if its in the room
	 *  --> DROP: user can drop an artifact in their inventory
	 *  --> USE: uses the artifact in the room user is currently (works for keys)
	 *  --> INVENTORY OR INVE: Lists out the artifacts in users possession (inventory)
	 */
	public void play() {
		
		System.out.println("\n\t\t\t" + name + "\n");		
		
		System.out.println("Enter what you would like to do in the following format:"
				+ "\n\t1. GO dir 	(dir -- Abbrivations or Full Name)"
				+ "\n\t2. LOOK		(Displays the Artifacts present in the Room)"
				+ "\n\t3. DISPLAY	(Displays all the info about the current character and some details about the room it is in.)"
				+ "\n\t4. GET artifact		(Picks up the artifact named)"
				+ "\n\t5. DROP artifact	(Drops the the artifact named)"
				+ "\n\t6. USE artifact		(Uses the artifact named in the Current room)"
				+ "\n\t7. INVENTORY or INVE	(List the artifacts in your possession and it's values)"	
				+ "\n\t8. QUIT or EXIT + 'ALL' will result exit from the game."
				+ "\n\t\t To Exit just the current player use EXIT or QUIT along without ALL"
				+ "\n\n***** Use Caps, Don't Use Caps its up to You,"
				+ " Who am I to judge? *****\n"
				+ "\n\nNOTE: Each player gets one turn and then they wait until all the players in the game"
				+ "\nhave had their turn. Using any of the above commands counts as turn excluding LOOK and "
				+ "\nINVENTORY/INVE."
				+ "\n\nUseful Tip: Display provides relevent info that will help you make make your"
				+ "\nnext move. You get 3 hints (or Displays) after that you are on your own."
				+ "\nDisplays also come at a cost of a turn. Using a display will mean you get the info but lose that turn.");


		Character c = charc.get(0);
		int i = 0;

		//System.out.println(charc.size());

		//Curr = Character.getCurrentPlace(c);			//start place
		//c.makeMove();
		
		while(true) {

			//Character c = charc.get(1);
			c.makeMove();
			if(!c.nextTurn()){
		
				if(i >= charc.size()-1){
					i = 0;
				} else {
					i++;
				}
	
				c = charc.get(i);

			}

		}
		
	}
}

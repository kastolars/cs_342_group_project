/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Place Class -- Holds the the ArrayList for Directions, Artifacts and Places
 *  			 Place ArrayList is static so that it can be accessed from all the classes.
 *  			 Additionally has holds two static functions -- getDescription and getPlaceByID
 *  			 so that they can be called from other classes to have reusable functions.
 *  			 Descriptions for the functions are provided below. Added 4 other functions:
 *  				--> addArtifacts -> adds the artifacts to the this places collection
 *  				--> useKey -> uses the key send in from artifacts class on all the directions
 *  								in this place
 *  				--> pickArtifact -> takes in a string and returns the artifact with that name
 *  									and removes that artifact from the collection to indicated
 *  									user has picked up the item
 *  				--> lookArtifact -> prints out the artifacts in the places collection
 *  
 *  			 New Additions: The only new Addition in this is class that Place now has a list of
 *  			 characters that are present in that place. This gets updated as Characters move from
 *  			 place to place.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Place {

	private int ID;
	private String name;
	private String description;
	public static int numAdded;
	private static boolean nowhere = false;
	private static boolean exit = false;
	private static int count = 0;				//temp variable for testing
	
	private ArrayList<Direction> add_dir = new ArrayList<Direction>();
	private ArrayList<Artifact> add_artf = new ArrayList<Artifact>();
	private ArrayList<Character> add_char = new ArrayList<Character>();
	
	public static ArrayList<Place> place = new ArrayList<Place>();	//stores all the places in game
	
	public Place (Scanner place_scn, double version) {
		
		String next = CleanLineScanner.getCleanLine(place_scn);
		if(next.equals("\0") || next.equals(null)) {
			
			//add exit location
			/* Creates and adds 2 places at the beginning of reading the Places
			 * 		--> Nowhere - ID = 0: a dead end
			 * 		--> Exit - ID = 0: ends the game
			 * Uses booleans to determine if they are the first and second Places
			 * to be added into the collection
			 */
			if(!nowhere) {
				this.ID = 0;
				this.name = "Nowhere";
				this.description = "Looks like you played yourself\nNow you will "
									+ "spend eternity here waiting for help"
									+ " that you won't get....\nSo much for Advanture...";
				place.add(this);
				nowhere = true;
			} else if(!exit && nowhere) {
				this.ID = 1;
				this.name = "Exit";
				this.description = "Leave If you will, but Adventure lies ahead\n";
				place.add(this);
				exit = true;
			}
			//return to the loop
		} else {
			/* This will add read the line(s) to extract the information for the Place
			 * the format is similar to one provided in the GDF format file. The first line is
			 * the ID followed by the name, second line is number of lines in description and
			 * and followed by the description in the rest.
			 */
			String[] data = next.split(" ", -1);
			try {
				this.ID = Integer.parseInt(data[0]);		//get the ID
			} catch (NumberFormatException nfe){
				
			}
			
			//Combine the word(s) of the the name together
			//and store it in name
			for(int i = 1; i < data.length; i++) {
				if(i == 1) {
					this.name = data[i];
				} else {
					this.name += data[i];	
				}
				
				this.name += " ";
			}
			
			this.name = this.name.trim();
			this.description = CleanLineScanner.getDescription(place_scn);		//calls the function to get the descrp
			place.add(this);
			numAdded = place.size();
			//this.print();
			//count++;
		}
		
	}


	//returns the first place listed in the data file
	public static int firstPlaceID() {
		return place.get(2).ID;
	}
	
	
	/* This function is used to get Place from ID. Called from direction class
	 * to get the "to" and "from" places for the direction. Returns the Place 
	 * that has the same ID as the ID sent in.
	 */
	public static Place getPlaceByID(int id) {
		
		for(int i = 0; i < place.size(); i++) {
			int idget = place.get(i).ID;
			
			//checks the id of the place with the from/to place ids
			//does absolute value because from/to values may be negative
			//to indicate lock/unlock directions. returns the place with
			//same id
			if(idget == Math.abs(id)) {
				return place.get(i);
			}
		}
		
		return null;
	}
	
	
	/* Uses the key on all the directions in this place by calling useKey
	 * in direction.
	 */
	public void useKey(Artifact key) {
		
		for(int i = 0; i < this.add_dir.size(); i++) {
			add_dir.get(i).useKey(key);
		}
	}
	
	/* Prints out all the artifacts in the current room
	 * if no artifacts are present it lets the user know so
	 */
	public void lookArtifact() {
		
		if(this.add_artf.size() == 0) {
			System.out.println("\nThere are no artifacts in this room");
		} else {
			for(int i = 0; i < this.add_artf.size(); i++) {
				this.add_artf.get(i).print();
			}
		}
	}
	
	
	/* Takes in a String for name of the artifact and searches through the
	 * artifact arraylist to look for the artifact in it if it finds it
	 * returns the artifact back to Game where it will be added to the 
	 * user's inventory, and removes the artifact from the place's collection
	 * of artifacts. If it can't find the artifact lets the user know there is
	 * no such artifact and returns the NULL
	 */
	public Artifact pickArtifact(String a, Character c) {
		Place curr = Character.getCurrentPlace(c);
		Artifact pickup;
		
		for (int i = 0; i < curr.add_artf.size(); i++) {
			if(curr.add_artf.get(i).name().equalsIgnoreCase(a)) {
				pickup = curr.add_artf.get(i);
				curr.add_artf.remove(i);
				System.out.println("\n--->" + pickup.name() + " picked up");
				return pickup;
			} else if(i == curr.add_artf.size() - 1) {
				System.out.println("\nThere is no artifact called " + a + " in this room");
				return null;
			}
		}
		
		if(c instanceof Player){
			System.out.println("\nThere are no artifacts in this room");
		}

		return null;
	}
	

	/* Function to get a random artifact name in the current room
	 * in that index position in the Artifact arraylist of the characters current
	 * place. Used by NPC decision maker to get a random artifact from the current
	 * room.
	 */
	public String getArtifact(){
		//Place curr = Character.getCurrentPlace(c);
		//Random rand = new Random();

		//int index = 0 + rand.nextInt(this.add_artf.size());

		if(this.add_artf.size() == 0) {
			return " ";
		}
		
		Collections.shuffle(add_artf);
		return this.add_artf.get(0).name();
	}
	

	//returns the name
	public String name() {
		return name;
	}
	
	//returns the description
	public String description() {
		//returns the description of the place
		return description;
	}
	
	//adds direction to the collection of this place
	public void addDirection(Direction d) {
		add_dir.add(d); 		//adds direction to the place
	}
	
	//adds artifacts to the collection of this place
	public void addArtifact(Artifact a) {
		add_artf.add(a);
		//count++;
		//System.out.println(count + "\n");
	}
	
	//add and remove characters from the arraylist
	public void addCharacter(Character c) {
		add_char.add(c);
	}
	
	public void removeCharacter(Character c) {
		add_char.remove(c);
	}
	
	//follows the direction and user wants to go and if the room is
	//locked or the direction doesn't exist lets the user know and returns
	//back to the current place
	public Place followDirection(String dir) {
		//checks if this place has a valid unlocked direction
		
		for(int i = 0; i < add_dir.size(); i++) {
			if(add_dir.get(i).match(dir)) {			//check if dir is a direction the place holds
				return add_dir.get(i).follow();		//returns from if room is locked, and to if the room in not
			}
		}

		return this;
	}
	
	//Provides user with information that *might* be useful to them
	public void display() {
		System.out.println("\nName: " + this.name +
							"\nDescription: " + this.description);
	}

	//prints info for debugging
	public void print() {
		System.out.print("\nID: " + ID + "\nName: " + name + "\nDesc: " + description);
		
	}
	
}

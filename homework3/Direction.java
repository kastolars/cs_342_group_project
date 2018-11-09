/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Direction Class -- Holds the enum DirType with 18 different directions
 *  			 and 1 for NONE incase the a room leads nowhere.
 *  			 The enum has following functions:
 *  				--> toString: returns the text version of the direction
 *  				--> match(String): returns true if the string is same as text or abbr
 *  				--> convert: converts the direction string in to DirType to maintain
 *  							 compatibility with other functions.
 *  			 The Direction class has 1 new function, useKey(), that matches the 
 *  			 keyPattern of the artifact sent in and the lockPattern of the direction
 *  			 if they match toggles the lock on the direction (details below).
 */


import java.util.ArrayList;
import java.util.Scanner;


public class Direction {

	//private static ArrayList<Direction> new_dir = new ArrayList<Direction>();
	private int ID;
	private Place from;
	private Place to;
	private DirType dir;
	private int lockPattern;
	public static int numAdded;
	
	
	public enum DirType {
		NONE("None", "NONE", 0),
		NORTH("North", "N", 1),
		SOUTH("South", "S", 2),
		EAST("East", "E", 3),
		WEST("West", "W", 4),
		UP("Up", "U", 5),
		DOWN("Down", "D", 6),
		NORTHEAST("Northeast", "NE", 7),
		NORTHWEST("Northwest", "NW", 8),
		SOUTHEAST("Southeast", "SE", 9),
		SOUTHWEST("Southwest", "SW", 10),
		NORTHNORTHEAST("North-Northeast", "NNE", 11),
		NORTHNORTHWEST("North-Northwest", "NNW", 12),
		EASTNORTHEAST("East-Northeast", "ENE", 13),
		WESTNORTHWEST("West-Northwest", "WNW", 14),
		EASTSOUTHEAST("East-Southeast", "ESE", 15),
		WESTSOUTHWEST("West-Southwest", "WSW", 16),
		SOUTHSOUTHEAST("South-Southeast", "SSE", 17),
		SOUTHSOUTHWEST("South-Southwest", "SSW", 18);
		
		private final String text;
		private final String abbr;
		private final int tag;
		
		private DirType(String text, String abbr, int tag) {
			this.text = text;
			this.abbr = abbr;
			this.tag = tag;
		}
	
		//converts a direction tag to it's String
		//Only used for randomizing NPC directions
		public static String tagToString(int t) {
			for(DirType dir : DirType.values()) {
				if(t == dir.tag){
					return dir.toString();
				}
			}

			return "None";
		}


		//returns the text version of the direction
		public String toString() {
			return this.text;
		}
		
		//if the user direction matches the text or abbr returns true
		public boolean match (String s) {
			
			if(this.text.equalsIgnoreCase(s) || this.abbr.equalsIgnoreCase(s)) 
				return true;

			return false;
			
		}
		
		//converts the String direction read in from the file to DirType
		public static DirType convert (String s) {
			//this.s = s;
			//return s.equalsIgnoreCase(toString());
			for(DirType dir : DirType.values()) {
				if(dir.text.equalsIgnoreCase(s) || dir.abbr.equalsIgnoreCase(s)) {
					return dir;
				}
			}
			
			return DirType.NONE;
			
		}
	}
	
	public Direction (Scanner dir_scn, double version) {
		
		
		String next = CleanLineScanner.getCleanLine(dir_scn);
		
		if(next.equals("\0") || next.equals(null)) {
			//return to the loop
		} else {
			/* This will add read the line(s) to extract the information for the Direction
			 * the format is similar to one provided in the GDF format file. The first value
			 * is ID of the direction, second is FromID, third Direction, fourth ToID, and 
			 * fifth lockPattern. The string is split using spaces and read accordingly.
			 */
			String[] data = next.split(" ", -1);
			this.ID = Integer.parseInt(data[0]);
			this.from = Place.getPlaceByID(Integer.parseInt(data[1]));
											//gets the place from ID using the function in Place
			this.dir = DirType.convert(data[2]);		//converts the string to DirType
			this.to = Place.getPlaceByID(Integer.parseInt(data[3]));
											//gets the place from ID using the function in Place
			this.lockPattern = Integer.parseInt(data[4]);
			
			if(Integer.parseInt(data[3]) <= 0) {
				this.ID = this.ID * -1;
			}
			
			numAdded++;				//increment to indicate direction was created
			from.addDirection(this);
		}
		
	}
	
	
	/* Checks if the lockPattern and keyPattern match and if they do toggles
	 * lock to indicate lock/unlock of the room. Toggling here is just multipling
	 * ID by -1, therefore if ID < 0 it's locked.
	 */
	public void useKey(Artifact artf) {
		
		if(artf.pattern() == lockPattern) {
			ID = ID * -1;
			
			if(ID > 0) {
				System.out.println("Someone Unlocked a Room. Hurry before it gets locked again...\n");
			} else {
				System.out.println("Someone Locked a Room. Choose your path carefully...\n");
			}
		}
		
	}
	
	//calls match() in enum DirType to see if the string matches any directions listed
	public boolean match(String s) {
		return dir.match(s);
	}
	
	
	
	//checks if the room is locked or not, used by follow() to determine if
	//user has access to a room or not
	public boolean isLocked() {
		
		//if room is locked return true else false
		if(ID < 0) {
			return true;
		}
		return false;
	}

	
	//Companion function of followDirection, checks if the room is locked or not,
	//if it is returns the current place back if its unlocked returns the "to" place of
	//this direction
	public Place follow() {
		
		//if the room is not locked then return "to" place
		if(!this.isLocked()) {
			return to;
		} else {
			//if the room is locked then return "from" place with a print statements
			System.out.println("\nRoom is locked. Try again");
			return from;
		}
		
	}
	
	
	//print info for debugging
	void print() {
		
		System.out.print("\nID: " + ID + "\nFrom: " + from.name() + "\nTo: " + to.name() +
																	"\nDirection: " + dir.toString() +
																	"\nLock Pattern: " + lockPattern
															        + "\n");
		
	
	}

	

}

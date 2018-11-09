/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Artifact Class -- Holds the constructor for artifacts and adds them to
 *  			 to the Artifacts collection in Place class. Additionally has the use()
 *  			 function that will use the artifact in the current room (currently only
 *  			 keys). When reading through the file if the placeID is less than 0 that means
 *  			 Artifact is in player's possession, it's greater than 0 than it in room with ID
 *  			 and if it's 0 we assign a random room to the artifact and put it there.
 */



import java.util.Random;
import java.util.Scanner;

public class Artifact {

	//private static ArrayList<Artifact> artf = new ArrayList<Artifact>();
	private String name;
	private int value;
	private int size;
	private String description;
	public static int numAdded;
	
	private int ID;
	private int destID;
	private int keyPattern;

	public Artifact(Scanner artf_scn, double version) {
		
		Random rand = new Random();
		
		String next = CleanLineScanner.getCleanLine(artf_scn);
		
		
		/* This will add read the line(s) to extract the information for the Artifacts
		 * the format is similar to one provided in the GDF format file. The first line is
		 * the placeID, second line has Artifact ID, value, size, and keyPattern. To extract
		 * this the line was split using space as a reference and each index of String[] is
		 * derived from the GDF format file provided.
		 */
		if(next.equals("\0") || next.equals(null)) {
			//return
		} else {
			
			destID = Integer.parseInt(next);
			
			next = CleanLineScanner.getCleanLine(artf_scn);			//gets the clean line
			String data[] = next.split(" ", -1);		//splits the line based on spaces
			
			this.ID = Integer.parseInt(data[0]);			//ID
			this.value = Integer.parseInt(data[1]);			//value
			this.size = Integer.parseInt(data[2]);			//size
			this.keyPattern = Integer.parseInt(data[3]);	//keyPattern
			
			//Get name -- 4th element in the string
			//loop to add multiple word names together
			for(int i = 4; i < data.length; i++) {
				if(i == 4) {
					this.name = data[i];
				} else {
					this.name += data[i];
				}
				this.name += " ";
			}
			
			this.name = this.name.trim();
			this.description = CleanLineScanner.getDescription(artf_scn);		//calls the function in Place that parses the
																	//description

			//character
			if((destID < 0)) {
				Character User = Character.getCharacterByID(destID);
				User.addUsrArtf(this);
			} else if(destID > 0) {
			  //place
				Place Dest = Place.getPlaceByID(destID);
				Dest.addArtifact(this);
			} else {
				//random place
				int random = rand.nextInt(Place.place.size() - 2) + 1;
				Place Dest = Place.place.get(random);
				Dest.addArtifact(this);
			}
			
			numAdded++;						//increment the counter to add indicated artifact was created
			
			//placeID.addArtifact(this);		//adds to the artifact collection of the the placeID Place
		}
	}
	
	public int value() {
		//returns the value?? of the artifact
		return value;
	}
	
	public int size() {
		//returns the mobility of the artifact
		return size;
	}
	
	public int pattern() {
		//returns the keyPattern of the artifact
		return keyPattern;
	}
	
	//returns name of the artifact
	public String name() {
		return name;
	}
	
	//returns the description of the artifact
	public String description() {
		return description;
	}

	/* uses the artifact:
	 * -- in case of the Key -- get Current place, from game class
	 * 						 -- pass the key to useKey() of the curr place
	 */
	public void use(Character c) {
		
		if(this.name().contains("key")){
			Place curr = Character.getCurrentPlace(c);
			curr.useKey(this);
		} else if (c instanceof Player) {
			System.out.println("\nThe artifact you are trying to use is not a Key\n");
		}

	}
	
	
	//prints the information for debugging
	public void print() {
		System.out.println("\nName: " + name + "\nID: " + 
							ID + "\tvalue: " + value + "\tMobility: " + size +
							"\nDescription: " + description);
	}
}

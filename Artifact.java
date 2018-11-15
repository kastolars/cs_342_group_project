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



import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;

public class Artifact {

	private static ArrayList<Artifact> artf = new ArrayList<Artifact>();

	private String name;
	private int value;
	private int size;
	private String description;
	public static int numAdded;
	
	private int ID;
	private int destID;
	private int keyPattern = 0;

	public Artifact(int ID, int value, int size, String name, String desc, int keyPattern, int destID) {


		this.ID = ID;
        this.value = value;
        this.size = size;
        this.name = name;
		this.description = desc;
		this.keyPattern = keyPattern;
		this.destID = destID;

		Random rand = new Random();

		//character
        try{
		if((destID < 0)) {
			Character User = Character.getCharacterByID( Math.abs(destID) );
            System.out.println(User);
			User.addArtifact(this);
		} else if(destID > 0) {
			//place
			Place Dest = Place.getPlaceById(destID);
			Dest.addArtifact(this);
		} else {
			//random place
			Place Dest = Place.getPlaceById( Place.getRandomPlaceID() );
			Dest.addArtifact(this);
		}
        }catch(Exception e){ e.printStackTrace(); }

		numAdded++;
		//placeID.addArtifact(this);		//adds to the artifact collection of the the placeID Place
		artf.add(this);



	}

	public Artifact (int id, int value, int meta, String name, String description) {
		this.ID = id;
		this.value = value % 10;
		this.name = name;
		this.description = description;
		this.keyPattern = 0;
	}


	//getter for arraylist size
	public static int GetSize() {
		return artf.size();
	}

	public int getMeta() {
		return 0;
	}

	public void updateMeta(int meta) {
		return;
	}

	/* DON'T NEED IT REMOVE ON CLEAN UP + CLEAN UP WEAPON CLASS
	public void SetDestination(int destID) {

		//Random rand = new Random();

		if((destID < 0)) {
			Character User = Character.getCharacterByID(destID);
			User.addArtifact(this);
		} else if(destID > 0) {
		  //place
			Place Dest = Place.getPlaceByID(destID);
			Dest.addArtifact(this);
		}

	}*/
	
	public int ID() {
		//returns the ID of the artifact
		return ID;
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

		if(this.name().contains("key")) {
			System.out.println("\nWrong Use Method\n");
		}

		if(this.name().contains("Torch")) {

		}
		
		/*if(this.name().contains("key")){
			Place curr = Character.getCurrentPlace(c);
			curr.useKey(this);
		} else if (c instanceof Player) {
			System.out.println("\nThe artifact you are trying to use is not a Key\n");
		}*/

	}

	public static String idToName(int id) {
		
		for(int i = 0; i < artf.size(); i++) {
			if(artf.get(i).ID == id) {
				return artf.get(i).name();
			}
		}

		return null;
	}

	public static void display() {
		for(int i = 0; i < artf.size(); i++){
			artf.get(i).print();
		}
	}
	
	
	//prints the information for debugging
	public void print() {
		System.out.println("\nName: " + name + "\nDescription: " + description);
	}

    // return a random valid item
    public static String randItem( ArrayList<Artifact> items ){
        ArrayList<Artifact> tmp = new ArrayList<Artifact>( items );

        Collections.shuffle(tmp); // randomize

        Random rand = new Random();
        int r = rand.nextInt();

        if( r % 10 == 0 )
            return "";

        try{
            return tmp.get(0).name;
        }
        catch( Exception e ){
            return "";
        }
    }
}


import java.util.Random;

public class Weapons extends Artifact {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    
    public Weapons (int ID, int value, int size, String name, String desc, int keyPattern, int destID) {

        /*this.ID = ID;
        this.value = value;
        this.size = size;
        this.name = name;
        this.desc = desc;*/

        super(ID, value, size, name, desc);

        this.keyPattern = keyPattern;
        this.destID = destID;


        Random rand = new Random();

        int random = rand.nextInt(Place.place.size()) + 2;

        //random = (int)(Math.random() * (Place.place.size() - 2)) + 2;
        
        //character
		/*if((destID < 0)) {
			Character User = Character.getCharacterByID(destID);
			User.addUsrArtf(this);
		} else if(destID > 0) {
		  //place
			Place Dest = Place.getPlaceByID(destID);
			Dest.addArtifact(this);
		} else {
			//random place
			//int random = rand.nextInt(Place.place.size() - 2) + 1;
			Place Dest = Place.place.get(random);
			Dest.addArtifact(this);
        }*/

        if(destID == 0) {
            this.destID = random;
            this.SetDestination(this.destID);
        } else {
            this.SetDestination(this.destID);
        }

    }


    @Override
    public void use(Character c) {

        System.out.println("Using Weapon...\n");

    }



}
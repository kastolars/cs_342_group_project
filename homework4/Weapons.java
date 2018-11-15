
import java.util.Random;

public class Weapons extends Artifact {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Weapons (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

        /*this.ID = ID;
        this.value = value;
        this.size = size;
        this.name = name;
        this.desc = desc;*/

        super(ID, value, size, name, desc, keyPattern, destID);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;

        /*Random rand = new Random();
        
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

    }


    @Override
    //lowers the damage of all the characters in the room expect for current
    //if this is an armour increases the health of the character
    public void use(Character c) {

        if(name().contains("Armour")) {
            // increase the health and removes the artifact from
            // character inventory


        } else {

            System.out.println("\nUsing Weapon...\n");
            //c.takeDamage(metaData);
            
        }
    }



}

import java.util.Random;

public class Potions extends Magic {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Potions (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

        /*this.ID = ID;
        this.value = value;
        this.size = size;
        this.name = name;
        this.desc = desc;*/

        super(ID, value, size, name, desc, keyPattern, destID, meta);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;

        
        /*Random rand = new Random();

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
        }*/
        
    }

    @Override
    public int getMeta() {
        return this.metaData;
    }

    @Override
    public void updateMeta(int meta) {
        this.metaData = meta;
    }

    @Override
    public void use(Character c) {
        //potions can used as shields or for magic
    }




}
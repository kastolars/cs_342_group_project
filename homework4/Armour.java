//import java.util.Random;

public class Armour extends Weapons {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Armour (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

        /*this.ID = ID;
        this.value = value;
        this.size = size;
        this.name = name;
        this.desc = desc;*/

        super(ID, value, size, name, desc, keyPattern, destID, meta);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;

    }




    @Override
    //lowers the damage of all the characters in the room expect for current
    //if this is an armour increases the health of the character
    public void use(Character c) {

        if(name().contains("Armour")) {
            // increase the health and removes the artifact from
            // character inventory
        }

    }



}
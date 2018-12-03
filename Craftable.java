/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Artifacts and is a type for wood, metal etc items that can
 *              be crafted into new things. Available for future expansions 
 */ 




import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Craftable extends Artifact {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    private int metaData;

    private static ArrayList<Artifact> crafted = new ArrayList<Artifact>();
    
    public Craftable (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

        super(ID, value, size, name, desc, keyPattern, destID);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;
        
    }
    

    @Override
    public int getMeta() {
        return metaData;
    }

    @Override
    public void updateMeta(int newData) {
        this.metaData = newData;

        //return 1;
    }



    @Override
    public void use(Character c) {
        //opens up recipe book and list the items that can be crafted
        // asks the user for what to make as craftable ID and calls
        // CraftNew


    }


    public static Artifact CraftNew(int rNum) {
        //return the new artifact and added to the player inventory

        return null;
    }


}
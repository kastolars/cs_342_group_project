/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Magic class to implement potions that can be used as
 *              sheilds. Future expansion is available.
 */


import java.util.Random;

public class Potions extends Magic {

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Potions (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

        super(ID, value, size, name, desc, keyPattern, destID, meta);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;

        
    }


    //gets the metaData
    @Override
    public int getMeta() {
        return this.metaData;
    }

    //updates the metaData
    @Override
    public void updateMeta(int meta) {
        this.metaData = meta;
    }

    //overrides use to implement potion usage
    @Override
    public void use(Character c) {
        //potions can used as shields or for magic
    }




}
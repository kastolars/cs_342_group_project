/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project 1
 * Descriptiop: Extention of Weapons class which it self extends Artifacts.
 *              The main goal of this function is to equip an armour that the 
 *              character has and increases the health of the character.
 */

public class Armour extends Weapons {

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Armour (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

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

            c.GainHealth(metaData);
            
        }

    }



}
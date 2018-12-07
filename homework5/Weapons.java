/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Artifact class to implement weapons that can be used as
 *              deal damage to opponents. Future expansion is available.
 */


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
        

    }


    @Override
    //lowers the damage of all the characters in the room expect for current
    //if this is an armour increases the health of the character
    public void use(Character c) {

        if(name().contains("Armour")) {
            // increase the health and removes the artifact from
            // character inventory


        } else {

            //System.out.println("\nUsing Weapon...\n");
            c.getString("\nUsing Weapon...\n");
            
            Character opponent = c.getCurrentPlace().getRandomCharacter(c);

            if(opponent == null) {
                //System.out.println("You can't attack anyone in this room...\n");
                c.getString("You can't attack anyone in this room...\n");
            } else {
                opponent.takeDamage(metaData);
            }

            //c.takeDamage(metaData);
            
        }
    }



}
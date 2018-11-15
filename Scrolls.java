/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Artifact class to implement learncing that can be used as
 *              skills. Future expansion is available.
 */


import java.util.Random;

public class Scrolls extends Artifact {

    /*private int ID;
    private int value;
    private int size;
    private String name;
    private String desc;*/

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Scrolls (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {

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
		if((this.destID < 0)) {
			Character User = Character.getCharacterByID(this.destID);
			User.addUsrArtf(this);
		} else if(this.destID > 0) {
		  //place
			Place Dest = Place.getPlaceByID(this.destID);
			Dest.addArtifact(this);
		} else {
			//random place
			int random = rand.nextInt(Place.place.size() - 2) + 1;
			Place Dest = Place.place.get(random);
			Dest.addArtifact(this);
        }*/
        
    }


    @Override
    public void use(Character c) {

        if(this.name().contains("Recipe")) {
            //print out all the recipes
            //ask the user to selet a "recipe" and call make?

            Recipe.display();
            
        } else if(this.name().contains("scroll")) {
            //print out what the scroll says

        }

    }



}
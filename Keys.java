/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Artifact for keys. Primary function is use which overrides
 *              the default use() function. This function use the key artifact
 *              to try and unlock directions.
 */ 

import java.util.Random;

public class Keys extends Artifact {

    private int keyPattern;
    private int destID;
    private int metaData;
    
    public Keys (int ID, int value, int size, String name, String desc, int keyPattern, int destID, int meta) {


        super(ID, value, size, name, desc, keyPattern, destID);

        this.keyPattern = keyPattern;
        this.destID = destID;
        this.metaData = meta;
    }


    //overrides the parent use to call useKey with this artifact to try
    // and unlock the direction.
    @Override
    public void use(Character c) {

        if(this.name().contains("key")){
			Place curr = c.getCurrentPlace();
			curr.useKey(this, c);

		} else if (c instanceof Player) {
			//System.out.println("\nThe artifact you are trying to use is not a Key\n");
		}

    }




}

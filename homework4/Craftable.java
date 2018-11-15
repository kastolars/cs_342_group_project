
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
		if((destID < 0)) {
			Character User = Character.getCharacterByID(destID);
			User.addUsrArtf(this);
		} else if(destID > 0) {
		  //place
			Place Dest = Place.getPlaceByID(destID);
			Dest.addArtifact(this);
		} else {
			//random place
			int random = rand.nextInt(Place.place.size() - 2) + 2;
			Place Dest = Place.place.get(random);
			Dest.addArtifact(this);
        }*/
        
    }

    public Craftable(int id, int value, int meta, String name, String desc) {
        super(id, value, meta, name, desc);

        crafted.add(this);
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

    /*public static int Recipe(Scanner recp_scn) {

        String next = CleanLineScanner.getCleanLine(recp_scn);

        if(next.equals("\0") || next.equals(null)) {

        } else {

            String name = next;
            next = CleanLineScanner.getCleanLine(recp_scn);

            String data[] = next.split(" ", -1);
            int id = Integer.parseInt(data[0]);
            int quantity = Integer.parseInt(data[1]);
            int info = Integer.parseInt(data[2]);

            next = CleanLineScanner.getCleanLine(recp_scn);
            String artfID[] = next.split("(?=//d)", -1);

            for(int i = 0; i < artfID.length; i++) {
                System.out.println(artfID[i]);
            }

            String description = CleanLineScanner.getDescription(recp_scn);

            System.out.println();

        }

        return crafted.size();
    }*/


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
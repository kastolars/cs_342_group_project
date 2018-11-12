

import java.util.Scanner;
import java.util.Random;


//HAVE TO MAKE SEPARATE .JAVA FILES FOR ALL THE ARTF TYPES!!!
//MAYBE FACTORY METHOD MIGHT BE EASY BUT WOULD I STILL NEED DIFFERENT
// .JAVA FILES???


public class ArtifactFactory {

    private int ID;
    private int value;
    private int size;
    private int keyPattern;

    private String name;
    private String desc;

    private int destID;

    private int typeID;

    public ArtifactFactory(Scanner artf_scn, double verNum) {

        //Random rand = new Random();
		
        String next = CleanLineScanner.getCleanLine(artf_scn);
        
		/* This will add read the line(s) to extract the information for the Artifacts
		 * the format is similar to one provided in the GDF format file. The first line is
		 * the placeID, second line has Artifact ID, value, size, and keyPattern. To extract
		 * this the line was split using space as a reference and each index of String[] is
		 * derived from the GDF format file provided.
		 */
		if(next.equals("\0") || next.equals(null)) {
			//return
		} else {
			
			destID = Integer.parseInt(next);
			
			next = CleanLineScanner.getCleanLine(artf_scn);			//gets the clean line
			String data[] = next.split(" ", -1);		//splits the line based on spaces
			
            this.ID = (Integer.parseInt(data[0]) % 100);	//ID
            this.typeID = Integer.parseInt(data[0]) / 100;  //typeID
			this.value = Integer.parseInt(data[1]);			//value
			this.size = Integer.parseInt(data[2]);			//size
			this.keyPattern = Integer.parseInt(data[3]);	//keyPattern
			
			//Get name -- 4th element in the string
			//loop to add multiple word names together
			for(int i = 4; i < data.length; i++) {
				if(i == 4) {
					this.name = data[i];
				} else {
					this.name += data[i];
				}
				this.name += " ";
			}
			
            this.name = this.name.trim();

            next = CleanLineScanner.getCleanLine(artf_scn);
            int info = Integer.parseInt(next);
            

			this.desc = CleanLineScanner.getDescription(artf_scn);		//calls the function in Place that parses the
                                                                        //description
                                                                    

            Artifact artf = TypeArtifact(typeID);

            if(typeID == 0 || typeID > 7){
                Random rand = new Random();
                //character
                if((destID < 0)) {
                    Character User = Character.getCharacterByID(destID);
                    User.addUsrArtf(artf);
                } else if(destID > 0) {
                //place
                    Place Dest = Place.getPlaceByID(destID);
                    Dest.addArtifact(artf);
                } else {
                    //random place
                    int random = rand.nextInt(Place.place.size() - 2) + 2;
                    Place Dest = Place.place.get(random);
                    Dest.addArtifact(artf);
                }
               // artf.SetDestination(destID);
            }		
        }
    }


    private Artifact TypeArtifact(int typeID) {

        switch(typeID) {
    
        case 1:
            return new Weapons(ID, value, size, name, desc, keyPattern, destID);
        case 2:
            return new Potions(ID, value, size, name, desc, keyPattern, destID);
        case 3:
            return new Magic(ID, value, size, name, desc, keyPattern, destID);
        case 4:
            return new Craftable(ID, value, size, name, desc, keyPattern, destID);
        case 5:
            return new Scrolls(ID, value, size, name, desc, keyPattern, destID);
        case 6:
            return new Maps(ID, value, size, name, desc, keyPattern, destID);
        case 7:
            return new Keys(ID, value, size, name, desc, keyPattern, destID);
        default:
            return new Artifact(ID, value, size, name, desc);
        }
    }
}


/*public class TypeArtifact {

    public static Artifact TypeOf(Scanner artf_scn, double verNum) {

        String next = CleanLineScanner.getCleanLine(artf_scn);

        if(next.equals("\0") || next.equals(null)) {
            //return
        } else {
            int typeId = Integer.parseInt(next);

            if(typeId == 1) {
                return Weapons();
            } else if (typeId == 2) {
                return Potions();
            } else if (typeId == 3) {
                return Magic();
            } else if (typeId == 4) {
                return Craftable();
            } else if (typeId == 5) {
                return Gems();
            } else if (typeId == 6) {
                return Scrolls();
            } else if (typeId == 7) {
                return Keys();
            } else if (typeId == 8){
                return Map();
            } else { return Artifact(); }

        }

    }
}*/


//for fighting -- swords, knifes etc
/*class Weapons extends Artifact {

    public Weapons(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }

}

//
class Potions extends Artifact {
    
    public Potions(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Magic extends Artifact {
    
    public Magic(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Craftable extends Artifact {

    public Craftable(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Gems extends Artifact {

    public Gems(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Scrolls extends Artifact {

    public Scrolls(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Maps extends Artifact {

    public Maps(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}

class Keys extends Artifact {

    public Keys(Scanner artf_scn, double verNum) {

        super(artf_scn, verNum);

    }
}*/

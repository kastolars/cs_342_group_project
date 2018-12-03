/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Artifact Factory that reads the GDF file and creates appropriate Artifact types.
 *              Types include Weapons, Craftable, Potions etc. Once done reading the a private
 *              function is called that returns the approriate artifact type using the typeID
 * 
 */



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
    private int info;

    public ArtifactFactory(Scanner artf_scn) {

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

            next = next.trim();
			destID = Integer.parseInt(next);
            // System.out.println( destID );
			
			next = CleanLineScanner.getLine(artf_scn);			//gets the clean line
			String data[] = next.split(" ", -1);		//splits the line based on spaces
			
            this.ID = (Integer.parseInt(data[0])) % 100;	 //ID
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

            next = CleanLineScanner.getLine(artf_scn);
            this.info = Integer.parseInt(next);
            

			this.desc = CleanLineScanner.getDescription(artf_scn);		//calls the function in Place that parses the
                                                                        //description
                                                                    

            Artifact artf = TypeArtifact(typeID);

        }
    }



    //Returns the appropriate Artifact type based on the type ID. No one has access
    //to this fucntion other than Afrtifact Factory.
    private Artifact TypeArtifact(int typeID) {

        switch(typeID) {
    
        case 1:
            return new Weapons(ID, value, size, name, desc, keyPattern, destID, info);
        case 2:
            return new Potions(ID, value, size, name, desc, keyPattern, destID, info);
        case 3:
            return new Magic(ID, value, size, name, desc, keyPattern, destID, info);
        case 4:
            return new Craftable(ID, value, size, name, desc, keyPattern, destID, info);
        case 5:
            return new Scrolls(ID, value, size, name, desc, keyPattern, destID, info);
        case 6:
            return new Maps(ID, value, size, name, desc, keyPattern, destID, info);
        case 7:
            return new Keys(ID, value, size, name, desc, keyPattern, destID, info);
        default:
            return new Artifact(ID, value, size, name, desc, keyPattern, destID);
        }
    }
}

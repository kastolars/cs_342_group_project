
import java.util.ArrayList;
import java.util.Scanner;

public class Recipe {

    private int ID;
    private int quantity;
    private int meta;

    private String artfID[];
    private String description;
    private String name;

    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public Recipe(Scanner recp_scn) {
        
        String next = CleanLineScanner.getCleanLine(recp_scn);

        if(next.equals("\0") || next.equals(null)) {

        } else {

            this.name = next;
            next = CleanLineScanner.getCleanLine(recp_scn);

            String data[] = next.split(" ", -1);
            this.ID = Integer.parseInt(data[0]);
            this.quantity = Integer.parseInt(data[1]);
            this.meta = Integer.parseInt(data[2]);

            next = CleanLineScanner.getCleanLine(recp_scn);
            this.artfID = next.split("(?=//d)", -1);

            for(int i = 0; i < artfID.length; i++) {
                System.out.println(artfID[i]);
            }

            this.description = CleanLineScanner.getDescription(recp_scn);
            System.out.println();

            recipes.add(this);
        }
            
    }

    public int ID() {
        return this.ID;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    //returns num of recipes
    public int GetSize() {
        return recipes.size();
    }
    
    /* PROBABLY DON'T NEED IT
    public boolean HasRecipe(String name) {

        for(int i = 0; i < recipes.size(); i++) {
            if(name.equalsIgnoreCase(recipes.get(i).name())) {
                return true;
            }
        }

        return false;
    }*/

    //takes in a character that is trying to make the artifact
    //checks if they character has enough materials to make it
    // if so makes the artifact and returns it...
    public Artifact MakeArtifact(Character c) {

        int k = 0;
        int j = 0;

        //check if the character has ingrediants for crafting...
        //check if the meta of artifact matches meta of recipe
        //if so make the artifact and return it
        if(artfID.length > 1) {
            k = c.HasArtifact(Integer.parseInt(artfID[0]));
            j = c.HasArtifact(Integer.parseInt(artfID[1]));

            if((k + j) > 0) {
                Artifact a = c.charArtifact(k);
                Artifact b = c.charArtifact(j);

                //check meta and update... maybe a function
            }

        } else {
            k = c.HasArtifact(Integer.parseInt(artfID[0]));

            if(k >= 0) {
                Artifact a = c.charArtifact(k);
                
                //check meta and update... maybe a function
            }
        }

        return null;
    }

}
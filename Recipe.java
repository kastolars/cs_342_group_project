/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Used to construct and create recipes from the GDF file.
 *              Has fucntion that take in character to create artifact from the
 *              known recepies and store it in their inventory.
 */


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
        
        String next = CleanLineScanner.getLine(recp_scn);
        //String next = recp_scn.nextLine();
        //next = next.trim();
        //System.out.println(next);

        if(next.equals("\0") || next.equals(null)) {

        } else {

            this.name = next.trim();

            //System.out.println(next);
            next = CleanLineScanner.getLine(recp_scn);
            next = next.trim();

            //System.out.println(next);

            String data[] = next.split(" ", -1);
            this.ID = Integer.parseInt(data[0]);
            this.quantity = Integer.parseInt(data[1]);
            this.meta = Integer.parseInt(data[2]);

            next = CleanLineScanner.getCleanLine(recp_scn);
            this.artfID = next.split(" ", -1);

            this.description = CleanLineScanner.getDescription(recp_scn);
            // System.out.println();

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
    
    //Checks if we have a recipe for the artifact
    //user wants to craft
    public static int HasRecipe(String name) {

        //System.out.println(name);

        for(int i = 0; i < recipes.size(); i++) {
            
            //System.out.print(recipes.get(i).name());
            if(name.equalsIgnoreCase(recipes.get(i).name())) {
                return i;
            }

        }

        return -1;
    }


    public static Recipe getRecipe(int index) {
        return recipes.get(index);
    }

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
                if((a.getMeta() >= this.quantity) && (b.getMeta() >= this.quantity)) {
                    
                    a.updateMeta(a.getMeta() - quantity);
                    b.updateMeta(b.getMeta() - quantity);
                    return this.Craft(c);

                } else {
                    //System.out.println("\nYou have insufficient Resources to craft " + this.name);
                    c.getString("\nYou have insufficient Resources to craft " + this.name);
                    return null;
                }
            }

        } else {
            k = c.HasArtifact(Integer.parseInt(artfID[0]));

            if(k >= 0) {
                Artifact a = c.charArtifact(k);

                if(a.getMeta() >= this.quantity) {

                    a.updateMeta(a.getMeta() - quantity);      //update quantity of left
                    return this.Craft(c);
                
                } else {
                    //System.out.println("\nYou have insufficient Resources to craft " + this.name);
                    c.getString("\nYou have insufficient Resources to craft " + this.name);
                    return null;
                }
            }
        }

        //System.out.println("\nYou don't the required resources to craft this item. Try again");
        c.getString("\nYou don't the required resources to craft this item. Try again");
        return null;
    }


    //crafts an artifact based on th recipe
    public Artifact Craft(Character c) {

        Artifact n;
        if(this.name.contains("Potions")) {
            n = new Potions (ID, 0, 10, name, description, 0, (-1*c.ID()), meta);
        } else if (this.name.contains("Armour")) {
            n = new Weapons(ID, 0, 10, name, description, 0, (-1*c.ID()), meta);
        } else {
            n = null;
        }

        return n;

    }

    //Displays the recipes in the recipe book
    public static void display() {
        for(int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            System.out.print("\n" + r.name + "\n-->Needs " + r.quantity + " of ");

            for(int j = 0; j < r.artfID.length; j++) {
                System.out.print(Artifact.idToName(Integer.parseInt(r.artfID[j])));
                
                if(j+1 == r.artfID.length-1) {
                    System.out.print(" and ");
                } else if(j+1 < r.artfID.length-1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }

}

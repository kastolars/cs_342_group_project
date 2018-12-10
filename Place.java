/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Place constructor holds all the information about the Place, id, name,
 *              description. It also holds a static collection of all the places along with
 *              list of all the characters and artifacts in the room.
 *              --> useKey() takes in a keya and characters and tries to open all the doors
 *                  or directions through this room.
 *              --> Generates random places, characters in places and directions in the places
 *                  that can be used by other classes.
 */



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.abs;

public class Place {
    protected int id;
    protected String name;
    protected String description;
    protected static Place start;
    protected static HashMap<Integer, Place> places = new HashMap<Integer, Place>();
    protected ArrayList<Direction> directions = new ArrayList<Direction>();
    protected HashSet<Character> characters = new HashSet<>();
    protected ArrayList<Artifact> artifacts = new ArrayList<>();

    public Place(int id, String name, String description) {
        this.id = abs(id);
        this.name = name;
        this.description = description;
        if (places.isEmpty()) {
            start = this;
        }
        places.put(this.id, this);
        // System.out.println(id);
    }

    public static Place getStart(){
        return start;
    }

    public void illuminate(){ }

    public static Place getPlaceById(int id){
        return places.get(id);
    }

    public void addDirection(Direction d){
        directions.add(d);
    }


    //Follow the direction passed in if the direction exist set to new place
    //if not return current place
    public Place followDirection(String s, Character c){
        
        //System.out.println("Place -> Direction: " + s + "\n");"
        //System.out.println("Place: " + c.name());
        
        for (Direction d : directions){
            //System.out.println("Checking Direction - " + d.type());
            //System.out.println("Place: " + d.match(s));
            if (d.match(s)){
                System.out.println("Place: Direction Found");
                try {
                    return d.follow(c, s);
                } catch (Direction.LockedDirectionException e){
                    return this;
                }
            }
        }
        return this;
    }

    public int ID() {
        return id;
    }

    //add characters to the collection
    public void addCharacter(Character c){
        characters.add(c);
    }

    //remove characters from the collections
    public void removeCharacter(Character c){
        characters.remove(c);
    }

    //add artifact to the collection
    public void addArtifact(Artifact a){
        artifacts.add(a);
    }

    public void removeArtifact(Artifact a){
        artifacts.remove(a);
    }

    public boolean isExit(){
        return id == 1;
    }

    public void display(Character c){
       
        // System.out.println(name);
        // System.out.println(description);
        // System.out.println("You see:");
        
        String s = "\n" + name + "\n\n" + description + "\n" + "You See:\n";
        //c.getString(name + "\n");
        //c.getString(description);
        c.getString(s);
        
        for (Artifact a : artifacts){
            //a.print();
            a.print(c);
        }

        c.getString("\nCharacters:\n");

        String names = "";

        for(Character d : characters){
            names += d.name() + "{" + d.health + "}, ";
        }

        names += "\n";
        c.getString(names);

        c.getString("\nDirections:\n");

        String cards = "";

        for(Direction d : directions){
            cards += d.printCardinals();
        }

        cards += "\n";

        c.getString(cards);

    }


    public HashSet<Character> getCharacters(){
        return characters;
    }

    //Use the key on all directions in the current room
    public void useKey(Artifact a, Character c) {
        for (Direction d : directions){
            d.useKey(a, c);
        }
    }

    public boolean hasArtifact(String s){
        for (Artifact a : artifacts){
            if (a.name().matches("(?i)" + s)){
                return true;
            }
        }
        return false;
    }

    //remove an item from the room and return it to the character
    public Artifact popItem(String s){
        for (Artifact a : artifacts){
            if (a.name().matches("(?i)" + s)){
                Artifact r = a;
                artifacts.remove(a);
                return r;
            }
        }
        return null;
    }

    public String name(){ return name; }

    public String randItem(){
        return Artifact.randItem( artifacts );
    }

    //get random directions
    public String getRandomDirection(){
        if (id == 0){
            return "";
        }
        ArrayList<Direction> randDir = new ArrayList<>(directions);
        Collections.shuffle(randDir);
        return randDir.get(0).type();
    }

    //Get random place from ID
    public static int getRandomPlaceID(){
        ArrayList<Integer> p = new ArrayList<Integer>( places.keySet() );
        p.remove(0);
        p.remove(1);
        Collections.shuffle(p);
        return p.get(0);
    }

    //Get random character present in the room
    public Character getRandomCharacter(Character c){
        ArrayList<Character> chars = new ArrayList<Character>(characters);
        Collections.shuffle(chars);
        
        if(chars.size() == 0) {
            return null;
        }

        Character a = chars.get(0);
        if (!(a.equals(c))){
            return a;
        }

        return null;
    }
}

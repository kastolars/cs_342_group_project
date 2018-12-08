import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * 
 *  DESCRIPTION:
 *  This class outlines the behavior of the Characters
 *  UI and AI are handled here by implementing DecisionMaker
 *  print() : void -- print the debuging info
 *  display() : void -- pretty print the info
 *  makeMove() : void -- decide on a move to make
 *  getByID() : Character -- static collection of characters
 *  Character(ID, name, desc) : Character -- make a character directly
 *  Character(Scanner) : Character -- have a character make themself from GDF
 *  getColor() : void -- random non-dup color support for better output
 *  popItem(String) : Artifact -- remove and return item from items
 *  selectItem(String) : Artifact -- return an item
 *  randItem() : String -- return the name for a given item
 *  addArtifact(Artifact) : void -- add an item to items
 *  moveToPlace(int) : void -- move the character to a place, for init
 *  takeDamage(int) : void -- does damage to the character
 *  gainHealth(int) : void -- increases the health of the character
 * 
 *  mana 
 *  health
 *  lives
 *  alive
 * 
 *  spells
 *      cast
 *      list
 *  
 *  interract
 */

public abstract class Character {
    private static ArrayList<String> colors;
    protected String color;
    protected int placeID;

    protected int health;
    protected int mana;
    protected int lives;
    protected boolean alive = true;

    private int ID;
    private String name;
    private String description;

    public IO interfaceType;

    private String outputBuffer;

    private ArrayList< Artifact > items;
    private final static ArrayList<Character> 
                        players = new ArrayList<Character>();

    public String name(){ return name; }

    public Character( Scanner parser ){
        getColor();

        //interfaceType = new IO();

        items = new ArrayList< Artifact >();

        placeID = 0; // nowhere
        try{
            placeID = Integer.parseInt( parser.next() ); // Place id (int)
            Place n = Place.getPlaceById(placeID);
            n.addCharacter(this);

        } catch(Exception e){ }

        if( placeID == 0 )
            placeID = Place.getRandomPlaceID();
            //placeID.addCharacter(this);
            Place n = Place.getPlaceById(placeID);
            n.addCharacter(this);
        
        try{
            ID = Integer.parseInt( parser.next() ); // Place id (int)
            //placeID.addCharacter(this);
        } catch(Exception e){
            ID = 0; // Bad ID
        }

        name = parser.nextLine().trim();

        try{
            int count = Integer.parseInt( parser.next() ); // # of desc lines
            description = "";
            for(int i = 0; i <= count; i++)
                description += "*" + parser.nextLine().trim() + "\n";
        } catch (Exception e) {
            System.out.printf("--ERROR-- Character description" + 
                                " # malformed after %s\n", this.name);
            System.exit(1);
        }

        addCharacter(this);
    }

    public Character( int ID, String name, String desc ){
        getColor();

        //interfaceType = new IO();

        this.ID = ID;
        this.name = name;
        this.description = desc;

        items = new ArrayList< Artifact >();

        addCharacter(this);
    }

    //toggles the GUI
    public void toggleGUI(Boolean tog){
        //user.setVisibility(tog);
    }

    public void setOutputBuffer(String s) {
        
        if(outputBuffer == null) {
            outputBuffer = s;
        } else {
            outputBuffer += s;
        }
    }

    //sets visibility of the output for a character
    public void setVisibility (Boolean turn) {
        interfaceType.setVisibility(turn);
    }


    //Takes in a String to be printed for this Character
    public void getString(String s) {

        //System.out.println(s);
        interfaceType.display(s);
        //calls IO.display(s) to print using current IO
        
    }
    

    //characters to the static collection of all characters 
    private void addCharacter(Character character){
        // if no duplicate IDs add(this)
        if( players.stream()
            .filter(x -> x.equals(this))
            .count() == 0 )

            players.add(this);
    }

    //gets the character what has passed in ID number
    public static Character getCharacterByID(int id){
        for(Character p : players)
            if(p.equals( id ))
                return p;

        System.out.println("\n--WARN-- Could not find character," + 
                            " returning default..\n");

        return getCharacterByID(0); // ID of noone
    }

    //Returns all the known players
    //Used to print info about characters or select randomly
    public static ArrayList<Character> knownCharacters(){
        return players;
    }

    // check if id given is the same as this.ID
    // equality is defined by Place ID
    public boolean equals( int id ){
        return this.ID == id;
    }


    // group members for printing
    public String toString(){
        return "Character:\n" + 
            " ID: " + ID +
            " Name: " + name +
            " Desc: " + description + "\n";
    }

    //adds artifacts to the collection of artifacts with the character
    public void addArtifact( Artifact item ){

        System.out.println("Character - " + item.name() );
        if( item == null ) return;
        System.out.printf("-+ Item [%s] Aquired +-\n", item.name() );
        items.add( item );
    }

    //Moves the character from one place to another
    //removes the character from current room and adds it to next one
    public void moveToPlace(int id){
        
        Place from = Place.getPlaceById(placeID);
        from.removeCharacter(this);
        placeID = id;
        Place to = Place.getPlaceById(placeID);
        to.addCharacter(this);
        
    }

    //returns the current place the character is in
    public Place getCurrentPlace(){
        return Place.getPlaceById( placeID );
    }

    // return a random valid Item
    public String randItem(){
        return Artifact.randItem( items );
    }

    // If the name of the artifact is in items remove that item from items
    // Return the Artifact that was removed
    // If no artifact found return null
    // depricated
    public Artifact popItem( String artifact ){
        Artifact foundItem;
        for(Artifact item : items)
            if(item.name().equalsIgnoreCase( artifact )){
                foundItem = item;
                items.remove(item);
                return foundItem;
            }
        return null;
    }

    // Return a item in items if it has the same name
    // If no such item in items return null
    public Artifact selectItem( String artifact ){
        for(Artifact item : items)
            if(item.name().equalsIgnoreCase( artifact )){
                return item;
            }
        return null;
    }

    public void print(Character c){ System.out.println( this ); }

    public void printItems(){ items.forEach( x -> x.print(this) ); }
    public void display(){ print(this); }
    public void makeMove(){}

    //changes color based on each character. Each one has different color
    private void getColor(){

        if( colors == null || colors.size() == 0 ){
            colors = new ArrayList<String>();
            colors.add( CColor.GREEN );
            colors.add(CColor.YELLOW);
            colors.add(CColor.BLUE);
            colors.add(CColor.PURPLE);
            colors.add(CColor.CYAN);
            colors.add(CColor.WHITE);
        }

        Collections.shuffle( colors );

        // System.out.println( color );
        color = colors.remove(0).toString();

    }

    //Factory method to create NPC or Player characters. Takes in a scanner
    //and reads the file to create different types of characters.
    public static void factory(Scanner parser){
        int placeID = 0; // nowhere
        try{
            placeID = Integer.parseInt( parser.next() ); // Place id (int)
        } catch(Exception e){ }

        if( placeID == 0 )
            placeID = Place.getRandomPlaceID();
        
        int ID;
        try{
            ID = Integer.parseInt( parser.next() ); // Place id (int)
        } catch(Exception e){
            ID = 0; // Bad ID
        }

        String name;
        name = parser.nextLine().trim();

        String description = "";
        try{
            int count = Integer.parseInt( parser.next() ); // # of desc lines
            for(int i = 0; i <= count; i++)
                description += "*" + parser.nextLine().trim() + "\n";
                
        } catch (Exception e) {
            System.out.printf("--ERROR-- Character description" + 
                                " # malformed after %s\n", name);
            System.exit(1);
        }

        if( name.toLowerCase().contains("ogre") ){
            new Ogre(placeID, name, description);
        }

        else if( name.toLowerCase().contains("grand pubah") ){
            new GrandPubah(placeID, name, description);
        }

        else if( name.toLowerCase().contains("sage") ){
            new Sage(placeID, name, description);
        }

        else if( name.toLowerCase().contains("leprechaun") ){
            new Leprechaun(placeID, name, description);
        } else {
            new NPC(parser);
        }

        // placeID, name, description
    }
    
    //Checks if the health is zero if so lowers the lives count
    abstract protected void lifeCheck();

    //Does damage to the character and checks if the health is less than
    //appropiate value and determine if lives need to be reduced.
    //if all players are dead exits the game
    public void takeDamage(int h){
        health -= h;

        if( health < 0 ){
            lifeCheck();
        }

        if( lives < 0 ){
            alive = false;
        }

        if(!alive){
            System.out.println("-- " + name + " have DIED, thanks for playing :) --");
            //players.remove(this);
        }

        if(players.isEmpty()){
            System.out.println("-- No players remain" + 
                        ", tata for now :) --");

            GameTester.quit();
        }

        System.out.println("\nHealth: " + health + "\nLives: " + lives + "\n");
    }

    //Increases the health of the character when they take a potion or put on
    //an armour
    public void GainHealth(int h){
        health += h;

        System.out.println("\nHealth: " + health + "\n");
    }

    protected void useMana(){
        mana -= 10;
    }

    //checks if the character has an artifact of id
    public int HasArtifact(int id) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).ID() == id) {
                return i;
            }
        }

        return -1;
    }

    //returns the artifact at index in the list of artifacts the character has
    public Artifact charArtifact(int index) {
        return items.get(index);
    }

    public int ID(){ return ID; }

    abstract public void cast(String spell, Place p);

}

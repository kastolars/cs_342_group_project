/**
 * AUTHOR: lpaltz2 -- hw3
 * DATE: October 25, 2018
 * 
 * DESCRIPTION:
 *  This class keeps track of the Game state and handles user input
 *  This class cleans the GDF so that a proper parse can be done
 *  The parser is handed to each constructor to create themself
 *  Has a list of Places that are in the game
 *  Has a list of items the player is holding
 *  Places can be added provided an instance of a Place
 *  Directions can be added given the IDs of the
 *          Direction, Place to, Place from, and String direction
 *  Play takes an int indicating the starting place or defaults 
 *          to 2 (the first place added to the game instance)
 * 
 *  Place.knownPlaces().get(0) is the current place
 * 
 *  cleanGDF(String) : String
 *  threeOne() -- version 3.1 support
 *  fourZero() -- version 4.0 support
 *  quit() -- handle safe exit conditions
 *  
 */
   
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    // private ArrayList< Artifact > items;
    
    private final String name;

    // call Game() with the clean file that was generated
    public Game(String gameName, int charNum) throws FileNotFoundException{
        this(new Scanner(new File( cleanGDF(gameName) )), charNum);
    }

    private void initialize(){
        // items = new ArrayList< Artifact >();

        addPlace  (new Place(1, "EXIT",
                                "\n*Congrats on reaching the exit" +
                                "\n*Perhaps a harder trial should be " +
                                "prepared to test your mettle" +
                                "\n*TaTa for now")
                    );  // include a Place exit -- ID 1 -- Place[1]

        addPlace  (new Place(0, "NOWHERE",
                                "\n*Abandon All Hope Ye Who Enter Here" +
                                "\n*Curse your luck that you have " + 
                                "wandered here" +
                                "\n*This is NOWHERE, there is no escape")
                    ); // include a Place NOWHERE -- ID 0 -- Place[2] -- spooky

        Character.knownCharacters().add( 
                new NPC( 0, "NOONE", "*A deathly skeleton\n" )
            );
    }

    // Generate a comment free GDF 
    // If cannot be generated return the original GDF
    //   ->  (will cause problems with parse alignment)
    // Open a Writer
    // If the line has a comment add up until that to the clean file
    // Otherwise add the whole line
    // The Professor has indicated this is acceptable over getCleanLine()
    public static String cleanGDF( String gameName ){
        try{
            PrintWriter writer = new PrintWriter(gameName + ".cl", "UTF-8");
            Files.lines(FileSystems.getDefault().getPath(".", gameName))
                    .forEachOrdered(x -> {
                        if(x.contains("//"))
                            writer.append(x.substring(0, 
                                        x.indexOf("//")) + "\n");
                        else
                            writer.append(x + "\n");
                    });
            writer.close();
            return gameName + ".cl";
        } catch (IOException e) {
            System.out.println("\n--WARN-- Could not clean gdf file.\n");
            return gameName;
        }
    }

    //Construct the game by stepping through the tokens and assigning them
    //new Objects will add themselves to list upping their reference count 
    //          (ie dont need to be stored)
    //Exits if GDF cannot be parsed due to malformation
    public Game( Scanner parser, int charNum ){
        initialize(); // Initialize lists and add hard-coded places
        parser.next(); // GDF token
        Float version = 0f; // Place holder

        try{
            version = Float.parseFloat( parser.next() );
            // version # token
        }
        catch(Exception e){
            System.out.printf("--ERROR-- GDF <version> malformed\n");
            quit();
            System.exit(1);
        }
        name = parser.nextLine().trim(); 
        // name token
        // System.out.println(version);

        if ( version == 3.1f ) 
            threeOne( parser, charNum ); // version 3.1 parser
        else if ( version == 4.0f )
            fourZero( parser, charNum ); // version 4.0 parser
        else{
            System.out.printf("--ERROR-- GDF Version not supported\n");
            quit();
            System.exit(1);
        }
    }

    private void threeOne( Scanner parser, int charNum ){
        int count; // counter for # of entries under each section

        // if charNum < 0 use max(1, default token)
        // else make charNum number of characters

        try{
            parser.next(); // Place token
            count = Integer.parseInt( parser.next() ); // Place count (int)
            for(int i = 0; i < count; i++)
                new Place( parser );
            
            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Direction (int)
            for(int i = 0; i < count; i++)
                new Direction( parser );

            parser.next(); // Artifact token
            count = Integer.parseInt( parser.next() ); // Artifact (int)
            for(int i = 0; i < count; i++)
                new Artifact( parser );

            Character.knownCharacters()
                        .add( new Player( 1, "Player One", 
                            "*An auto generated player one\n") );

            Character.getCharacterByID(1).moveToPlace(12);

        } catch (Exception e){
            System.out.printf("--ERROR-- Malformed number of { Atrifacts" +
                         " || Directions || Places }\n");
            quit();
            System.exit(1);
        }
        // System.out.println( "GAME::" + Place.getPlaceByID(23) );
        // Place.knownPlaces().forEach( x -> System.out.println(x+"\n"));
    }


    private void fourZero( Scanner parser, int charNum ){
        int count; // counter for # of entries under each section

        // if charNum < 0 use max(1, default token)
        // else make charNum number of characters

        try{
            parser.next(); // Place token
            count = Integer.parseInt( parser.next() ); // Place count (int)
            for(int i = 0; i < count; i++)
                new Place( parser );
            
            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Direction (int)
            for(int i = 0; i < count; i++)
                new Direction( parser );

            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Character (int)
            int players = 0;

            // if we need more players then are in the GDF auto generate NPC
            if(count < charNum){
                for(int i = 0; i <= charNum - count; i++)
                    new NPC( i*100,
                             "NPC " + i*100,
                             "*An auto generated NPC\n" );
            }
            else{
                count = charNum;
            }
            
            for(int i = 0; i < count; i++){
                String tok = parser.next();
                
                if( tok.equalsIgnoreCase("player") ){
                    new Player( parser );
                    players++;
                }
                else if ( tok.equalsIgnoreCase("npc") )
                    Character.factory( parser );
            }

            // you need at least one player -- no exceptions
            if(players == 0){
                new Player( 1, 
                            "Player One", 
                            "*An auto generated player one\n"
                        );

                Character.getCharacterByID(1).moveToPlace(12);
            }

            // walk until you find the appropriate token
            while ( !parser.next().equals("ARTIFACTS") ) ; // Get Artifact token

            count = Integer.parseInt( parser.next() ); // Artifact (int)
            for(int i = 0; i < count; i++)
                new Artifact( parser );

        } catch (Exception e){
            // e.printStackTrace();
            System.out.printf("--ERROR-- Malformed number of { Atrifacts" +
                         " || Directions || Places }\n");
            quit();
            System.exit(1);
        }
        // System.out.println( "GAME::" + Place.getPlaceByID(23) );
        // Place.knownPlaces().forEach( x -> System.out.println(x+"\n"));
    }

    public void play(){
        play(12);  // 12 is the default start location ( Enterance Hall )
    }

    public void play(int start){
        System.out.println("  WELCOME TO " + name + "\n");

        try{
            while( true ){
                Character.knownCharacters().forEach(
                    x -> {
                        if( !x.equals(0) )
                            x.makeMove();
                    }
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            quit();
        }
    }

    // If the name of the artifact is in items remove that item from items
    // Return the Artifact that was removed
    // If no artifact found return null
    // depricated
    // private Artifact popItem( String artifact ){
    //     Artifact foundItem;
    //     for(Artifact item : items)
    //         if(item.name().equalsIgnoreCase( artifact )){
    //             foundItem = item;
    //             items.remove(item);
    //             return foundItem;
    //         }
    //     return null;
    // }

    // depricated
    // private void displayRoom(){
    //     getCurrentPlace().print();
    // }

    // Return a item in items if it has the same name
    // If no such item in items return null
    // private Artifact selectItem( String artifact ){
    //     for(Artifact item : items)
    //         if(item.name().equalsIgnoreCase( artifact )){
    //             return item;
    //         }
    //     return null;
    // }

    // depricated
    // private Place getCurrentPlace(){
    //     return Place.knownPlaces().get(0);
    // }

    // depricated
    // private void setCurrentPlace( Place cur ){
    //     Place.knownPlaces().set(0, cur);
    // }

    public void addPlace( Place p ){
        // adds a place by creating a stream of Places that have the same ID
        // stream.size() == 0 implies no duplicate IDs
        if( Place.knownPlaces()
                    .stream()
                    .filter(x -> x.equals(p))
                    .count() == 0 ) {

            Place.knownPlaces().add(p);
        }
    }

    public void addCharacter( Character p ){
        // adds a place by creating a stream of Places that have the same ID
        // stream.size() == 0 implies no duplicate IDs
        if( Character.knownCharacters()
                    .stream()
                    .filter(x -> x.equals(p))
                    .count() == 0 ) {

            Character.knownCharacters().add(p);
        }
    }

    // depricated
    //used by GameTester::fillGame to load the game based on Place IDs
    //find Place from
    //find Place to
    //construct the relavent direction
    //if ID < 0 then lock()
    //add the direction to the from place
    public void addDirection(int dirID, int fromID, int toID, String dir){
        Place.knownPlaces()
                .stream()
                .filter(x -> x.equals(fromID))
                .findAny()
                .ifPresent(
                    from ->
                        Place.knownPlaces()
                                .stream()
                                .filter(x -> x.equals(Math.abs(toID)))
                                .findAny()
                                .ifPresent(
                                    to -> {
                                        Direction d = new 
                                            Direction (dirID, from, to, dir);

                                        if(toID < 0) d.lock();

                                        from.addDirection(d);
                                    }
                                )
                );
    }

    // handle the quitting of the game
    // remove the temporary .cl file
    // exit from the game -- ie kill program
    public static void quit(){
        try{
            Arrays.stream(new File(".")
                    .listFiles((f, p) -> p.endsWith(".cl")))
                    .forEach(File::delete);
                                            
        } catch(Exception e){
            System.out.println("Failed to remove *.cl");
        }

        System.exit(0);
    }

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        return name + ": \n" +
            Place.knownPlaces();
    }
}

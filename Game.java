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
   
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    // private ArrayList< Artifact > items;
    
    private final String name;

    private void initialize(){

        Character.knownCharacters().add( 
                new NPC( 0, "NOONE", "*A deathly skeleton\n" )
            );
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
        else if ( version == 5.2f )
            fiveTwo( parser, charNum ); // version 5.2 parser
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
            PlaceFactory.makePlaces( parser, count );

            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Direction count (int)
            DirectionFactory.makePlaces( parser, count );

            parser.next(); // Artifact token
            count = Integer.parseInt( parser.next() ); // Artifact (int)
            for(int i = 0; i < count; i++)
                new ArtifactFactory( parser );

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
            PlaceFactory.makePlaces( parser, count );
            
            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Direction count (int)
            DirectionFactory.makePlaces( parser, count );

            parser.next(); // Character token
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
                new ArtifactFactory( parser );

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

    private void fiveTwo( Scanner parser, int charNum ){
        int count;

        try{
            parser.next(); // Place token
            count = Integer.parseInt( parser.next() ); // Place count (int)
            PlaceFactory.makePlaces( parser, count );

            parser.next(); // Direction token
            count = Integer.parseInt( parser.next() ); // Direction count (int)
            DirectionFactory.makePlaces( parser, count );

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
                new ArtifactFactory( parser );

            while ( !parser.next().equals("RECIPES") ) ; // Get Recipe token

            count = Integer.parseInt( parser.next() ); // Recipe (int)
            for(int i = 0; i < count; i++)
                new Recipe( parser );
        }
        catch (Exception e){
            // e.printStackTrace();
            System.out.printf("--ERROR-- Malformed number of { Atrifacts" +
                        " || Directions || Places }\n");
            GameTester.quit();
            System.exit(1);
        }

    }

    public void play(){
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
            GameTester.quit();
        }
    }

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        return name + ": \n" +
            Place.knownPlaces();
    }
}

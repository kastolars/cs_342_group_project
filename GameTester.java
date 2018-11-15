/**
 * AUTHOR: lpaltz2 -- hw3
 * DATE: October 25, 2018
 * 
 * DESCRIPTION:
 *  This Class passes a GDF file to Game
 *  Game.play is then called
 *  Testing of features was done here
 * 
 *  accept cmdline args or ask user for # players and GDF file
 *  defaults are
 *      Players = -1
 *      GDF = mysticCity40.gdf
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameTester {

    public GameTester(){
        // .apply() calls each lamda from a high order function
        // use null to apply(Void)
        System.out.println( "NAMES" );
    }

    //GAME reference is constant -- cannot be assigned by can be modified
    private Game GAME;

    public void test(String mapName, String charNum){
        System.out.println( "\n-- SELECTED -- " +
                            mapName.toUpperCase() + " --" );

        int cNum = 0;
        try{
            cNum = Integer.parseInt( charNum );
            System.out.printf("-- SELECTED -- %d CHARACTERS --\n\n", cNum);
        }
        catch(Exception e){
            System.out.println("--WARN-- Bad number of characters selected " + 
                                "- defaulting\n");
            cNum = -1;
        }

        // create the game object with a name
        // cleans the file (removes comments)
        try{ 
            //GAME = new Game(mapName, cNum);
            GAME = new Game(

                new Scanner( new File( Game.cleanGDF(mapName) ) ), 
                cNum 
            );

            GAME.play();
        }
        catch(FileNotFoundException e){
            System.out.println("  Could not open file!! ");
            GAME = null;
        }
        //fillGame(mapName + ".gdf");
        //GAME.print();
    }

    // check to see if a file is a valid choice
    public boolean getFile(String s){
        try{
            return Arrays.stream(new File(".")
                        .listFiles((f, p) -> p.equalsIgnoreCase( s ) ))
                        .findAny()
                        .isPresent();
        }
        catch(Exception e){
            return false;
        }
    }

    public static void main(String[] args) {

        // System.out.print("Welcome, please enter your map" + 
        //                  " -- sixRooms or mysticCity -- ");
        // String input = System.console().readLine().toUpperCase();

        // create a tester
        GameTester GAME = new GameTester();

        // try to grab the gdf argument
        String input = "";
        String players = "-1";
        try{
            input = args[0];
        }
        catch(Exception e){
            input = "";
        }

        Scanner scanner = KeyboardScanner.getKeyboardScanner();

        if (input.equalsIgnoreCase("default"))
            input = "mysticCity40.gdf";

        // ask for input until a valid GDF is given
        while ( !GAME.getFile( input ) ){
            System.out.printf(" Enter a valid GDF file -> ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("default")){
                input = "mysticCity40.gdf";
                break;
            }
        }
        
        if ( args.length > 1 )
            GAME.test( input, (String)args[1] );
        else{
            System.out.print(" Number of Characters desired -> ");
            players = scanner.nextLine();
            GAME.test( input, players );
        }
    }
}
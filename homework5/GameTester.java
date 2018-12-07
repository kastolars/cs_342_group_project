/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
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

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class GameTester {

    public GameTester(){
        // .apply() calls each lamda from a high order function
        // use null to apply(Void)
        String luke = "Luke Paltzer -- lpaltz2\n";
        String kar = "Karol Stolarski -- kstola2\n";
        String ayu = "Ayush Patel -- apate324\n";
        String groNum = "Group: 34\n";
        System.out.println( luke + kar + ayu + groNum );
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

                new Scanner( new File( GameTester.cleanGDF(mapName) ) ), 
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
//            Files.lines(q)
                    .forEachOrdered(x -> {
                        if(x.contains("//"))
                            writer.append(x.substring(0, 
                                        x.indexOf("//")) + "\n");
                        else
                            writer.append(x + "\n");
                    });
            writer.close();

            FileInputStream stream = new FileInputStream(new File(gameName));
            
            return gameName + ".cl";
        } catch (IOException e) {
            System.out.println("\n--WARN-- Could not clean gdf file.\n");
            e.printStackTrace();
            return gameName;
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
            input = "MysticCity_52.gdf";

        // ask for input until a valid GDF is given
        while ( !GAME.getFile( input ) ){
            System.out.printf(" Enter a valid GDF file -> ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("default")){
                input = "MysticCity_52.gdf";
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

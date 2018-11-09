/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: GameTester class: To start the program, open the file, initialize the scanner to 
 *  			 read the file. Will pass thethe scanner off to game class where it will be used to 
 * 				 add places, direction and artifacts. Takes in command line argument as the file and
 * 				 if it can't open that file will ask the user to input the file again.
 * 				 The only new addition in this class is implementation of opening and loading the
 * 				 Game Data File and the Scanner to read. When the program begins it will try to load 
 * 				 the file passed in at through the command line and if it can't open that file it will 
 * 				 prompt the user to input the file name again.
 * 				 New Additions: Game Tester now has abitlity to take in number of players the user wants
 * 				 to start the game as and if the GDF didn't have that many players it will create additional
 * 				 to satisfy that need.
 */


import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class GameTester {

	public static void main(String[] args) throws FileNotFoundException {
	
		System.out.println("\nName: Ayush Patel\nnetID: apate324");
		
		String file;
		int numPlayers;
		if(args.length > 2) {
			file = args[0];
			for(int i = 1; i < args.length - 1; i++) {
				file += " " + args[i];
			}
			numPlayers = Integer.parseInt(args[args.length-1]);
		} else if (args.length == 2) {
			file = args[0];
			numPlayers = Integer.parseInt(args[1]);
		} else {
			file = args[0];
			numPlayers = 0;
		}
		
		//System.out.println(file + "\n"+ numPlayers);

		String workingDir = System.getProperty("user.dir");
		//file = workingDir + "\\" + file + ".txt";
		file = workingDir + "/" + file;
		
		File game = new File(file);
		
		Scanner game_scn;
		
		//if the command line file exists start scanning
		//else ask the user to input a file name manually again
		if(game.exists()) {
			game_scn = new Scanner (game);
		} else {
			System.out.println("\nCouldn't open the file, Please try again\n");
			System.out.println(">>> Enter the Game Data File to Load: ");

			Scanner scan = new Scanner(System.in);
			file = scan.nextLine();
			
			//file = workingDir + "\\" + file + ".txt";
			file = workingDir + "/" + file;
			
			File usr_game = new File(file);
			game_scn = new Scanner (usr_game);
			//scan.close();
		}
		
		Game Test = new Game(game_scn);
		int numCharacter = Test.numCharacter();
		String desc = "This is User created Player.";
		int p = Place.firstPlaceID();

		if(numCharacter < numPlayers) {
			System.out.println("There aren't enough players in the game.\n" +
							"Please enter names of Players you want to create\n");
			
			for(int i = 0; i < (numPlayers - numCharacter); i++) {
				//create more players
				System.out.print("Name: ");
				Scanner key = KeyboardScanner.getKeyScanner();
				String name = key.nextLine();
				Player user = new Player(i+1, name, desc, p);
				System.out.println("\t~" + name + " was created\n");

			}
		} else if(numPlayers == 0 && numCharacter == 0){
			//create 1 player not NPC
			System.out.println("There aren't any Players created in the game.\n" +
								"Please enter name of your Player:");
			Scanner key = KeyboardScanner.getKeyScanner();
			String name = key.nextLine();
			Player user = new Player(4, name, desc, p);
		}

		Test.play();
		game_scn.close();

		
	}

}

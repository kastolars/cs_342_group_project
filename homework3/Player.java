/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  Description: Player class -- Player is child of Character and it inherits most if not all of it
 *  			 functions and variables. When creating a character for GDF, the game will check if
 *  			 the character is NPC or player and then create that character by creating either a
 *  			 NPC or Player constructor which in turn will call Character constructor. This way
 *  			 Character has a list of all the characters of either type NPC or Player.
 */


import java.util.Scanner;

public class Player extends Character{
	
	//private ArrayList<Player> player = new ArrayList<Player>();
	
	//private Place location;
	
	/*public Player(int id, String name, String desc, Place loca) {
		
		super(id, name, desc);
		this.location = loca;
		
		//System.out.println(id + "\n" + name + "\n" + desc);
	}*/
	
	public Player(Scanner ply_scn, double version, int placeID) {
		
		super(ply_scn, version, placeID);
	}

	public Player(int id, String name, String desc, int placeID) {

		super(id, name, desc, placeID);

	}
	

}

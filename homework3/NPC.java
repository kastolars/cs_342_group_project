/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  Description: NPC class -- NPC is child of Character and it inherits most if not all of it
 *  			 functions and variables. When creating a character for GDF, the game will check if
 *  			 the character is NPC or player and then create that character by creating either a
 *  			 NPC or Player constructor which in turn will call Character constructor. This way
 *  			 Character has a list of all the characters of either type NPC or Player.
 */



import java.util.Scanner;

public class NPC extends Character {

	public NPC(Scanner npc_scn, double version, int placeID) {
		
		super(npc_scn, version, placeID);
		
	}

	//private Place location;
	
	/*public NPC(int id, String name, String desc, Place loca) {
		
		super(id, name, desc);
		this.location = loca;
		
		//System.out.println(id + "\n" + name + "\n" + desc);
	}*/
	
}

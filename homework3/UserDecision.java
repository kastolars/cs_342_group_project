/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: UserDecision -- This class implements DecisionMaker for the Player
 *  			 This will ask the user to put in the move they want to make and then create
 *  			 an Move using that input and return it.
 */

import java.util.Scanner;

public class UserDecision implements DecisionMaker {
	
	public Move getMove(Character c, Place p) {
		
		Scanner input = KeyboardScanner.getKeyScanner();
		String next = input.nextLine();

		//MoveType mv = MoveType.type(next);
		Move m = new Move(next);
		return m;
	}

}

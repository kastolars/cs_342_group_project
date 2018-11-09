/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: Contains the declaration of the function getMove that will be used by
 *  			 ArtificialDecision and UserDecision. This is an interface class.
 *  
 */
public interface DecisionMaker {

	public Move getMove(Character c, Place p);
	
}

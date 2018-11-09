/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  CS 342: Software Design
 *  Description: ArtificialDecision class: This class implements DecisionMaker for AI. It has
 *  			 getMove() function that takes in a Character and place to determine if what move
 *  			 the NPC AI can make. Using a random number it will pick from GO, GET, DROP, and USE
 *  			 movements to execute.
 *  			   --> GO: generates a string with "G0 (random Direction)"
 *  			   --> GET: generates a string with "GET (random artifact in the place)"
 *  			   --> DROP: generates a string with "DROP (random artifact in character inventory)"
 *  			   --> USE: generates a string with "USE (random artifact in the character inventory)"
 *  			 For the purpose of the project we will assume that the NPC knows all the charaters present
 *  			 in the room and in the player's inventory since it can't look, display, or view inventory.
 *  
 * 				 Returns an Move object that has the movetype and the arguments (direction, artifact name)
 */



import java.util.Random;

//import java.util.Scanner;

public class ArtificialDecision implements DecisionMaker {
	
	public Move getMove(Character c, Place p) {
		
		//Scanner input = KeyboardScanner.getKeyScanner();
        String next = "";
        
        Random rand = new Random();
        int random = 1 + rand.nextInt(4);

        //System.out.println(random);

        switch(random){

        case 1:
            next = "GO ";
            int dirRand = rand.nextInt(18);
            next += Direction.DirType.tagToString(dirRand);
            break;

        case 2:
            next = "GET ";
            next += p.getArtifact();
            //next += g.name();
            break;

        case 3:
            next = "DROP ";
            next += c.getArtifact();
            //next += d.name();
            break;

        case 4:
            next = "USE ";
            next += c.getArtifact();
            //next += u.name();
            break;

        default:
            next = "STAY";
            break;
        }

		//MoveType mv = MoveType.type(next);
        Move m = new Move(next);
        return m;
	}

}
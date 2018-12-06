package com.company;/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Move class -- Holds the MoveType Enum that has a list of moves either a player
 *  			 or a NPC can make. The Enum has type() method which takes in a string and returns
 *  			 MoveType that it matches. Move constructor takes in a string and generates a Move
 *  			 getting the MoveType associated to it and the arguments passed through that string.
 *  			 Other than this Move also has to methods to return MoveType and agruements.
 * 				
 * 				This class now holds the execute method that will create a move and excute it. The excecute
 * 				methods takes in a Character and its current place.
 */



public class Move {

	private String arg;
	private MoveType type;
	
	enum MoveType {
		
		
		STAY("Stay"),			//stay at the same place (same as saying None)
		GO("Go"),
		LOOK("Look"),
		DISPLAY("Display"),
		GET("Get"),
		DROP("Drop"),
		USE("Use"),
		CRAFT("Craft"),
		INVENTORY("Inventory"),
		INVE("Inve"),
		QUIT("Quit"),
		EXIT("Exit");
		
		
		private String move;
		
		private MoveType (String move) {
			this.move = move;
		}
		
		public String toString() {
			return this.move;
		}
		
		public static MoveType type(String move) {
			
			for(MoveType action : MoveType.values()) {
				
				if(action.move.equalsIgnoreCase(move)) {
					return action;
				}
			}
			
			return MoveType.STAY;
		}
		
	}

	
	public Move(String s) {
		
		int index = s.indexOf(" ");

		String action;
		if(index > 0){
			action = s.substring(0, index + 1);
			action = action.trim();
	
			this.arg = s.substring(action.length() + 1);
		} else {
			action = s;
			action = action.trim();
			this.arg = "";
		}
		
		this.type = MoveType.type(action);

		//System.out.println(this.arg);
		//System.out.println(this.type.toString());
	}

	public MoveType getType(){
		return this.type;
	}

	public String args(){
		return this.arg;
	}


	//this method now makes and executes moves based on the UI or AI input
	//takes in the character and the current place its in.
	public void execute(Character c, Place charLoc) {

		MoveType mv = getType();
		String arg = args();

         System.out.println(mv + " " + arg);

		switch(mv) {

			case GO:
				charLoc.removeCharacter(c);
				Place tempCurr = charLoc;

				if(c instanceof NPC && ( !charLoc.followDirection(arg).isExit() ) ){
					tempCurr = charLoc.followDirection(arg);
				} else {
					tempCurr = charLoc.followDirection(arg);
				}

				int id = tempCurr.ID();
				c.moveToPlace(id);

				if(tempCurr == charLoc && c instanceof Player){
					//this case only happens when user input direction does not exist in this room
					System.out.println("\nThere was either no Room in that direction or\n"
										+ "You entered wrong input check and Try Again.\n");
				} else {
					charLoc = tempCurr;
					//System.out.println(charLoc.name());
				}

				charLoc.addCharacter(c);
				//this.canRepeat = false;
				break;
			
			case LOOK:
				charLoc.display();
				// charLoc.lookArtifact();
				//this.canRepeat = true;
				break;
			

			case GET:

				if(charLoc.hasArtifact( arg ))
                    c.addArtifact( charLoc.popItem( arg ) );

				//canRepeat = false;
				break;
			
			case DROP:
				Artifact a = c.popItem(arg);

				if(a != null) {
					charLoc.addArtifact(a);
					System.out.println("\nDroped " + arg + " in " + charLoc.name());
				} else if ( a == null && c instanceof Player) {
					System.out.println("\nYou Don't have any artifact called " + arg);
				}

				//this.canRepeat = false;
				break;
			
			case USE:

				Artifact ab = c.selectItem(arg);

				if(ab != null) {
					ab.use(c);
				} else if (ab == null && c instanceof Player) {
					System.out.println("\nYou Don't have any artifact named " + arg);
				}
				break;

			case INVE: case INVENTORY:

				//this.canRepeat = true;

				c.printItems();
				break;

			case CRAFT:

				int index = Recipe.HasRecipe(arg);
				//System.out.println(index);
				if(index >= 0) {
					Recipe r = Recipe.getRecipe(index);
					Artifact aa = r.MakeArtifact(c);

					System.out.println(aa.name());

					//a.print();
					if(aa != null) {
						System.out.println("\n\t~ " + aa.name() + " was crafted. It is now available in your inventory\n");
					}
				} else {
					System.out.println("Nothing");
				}

				//this.canRepeat = true;
				break;

			case EXIT: case QUIT:

				if(arg.equalsIgnoreCase("All")){
					System.out.println("\nExiting Game...\nGoodbye :-)\n\n\t~ All players left\n\n");
					GameTester.quit();
				}

				System.out.println("\nGoodbye " + c.name());
				System.out.println("\t~ " + c.name() + "~  has left the game");

				//this.exit();
				// System.out.println("\nThere are still " + playable + " players in the game");
				//this.canRepeat = false;
				break;
				//System.exit(0);
			
			
			default:
				System.out.println("\nYou couldn't do anything. Maybe try doing some else...\n");
				//this.canRepeat = true;
		
		}
	}
}

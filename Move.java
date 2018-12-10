/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 5: Group Project
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

		arg = "";
		String input = s.toUpperCase().trim();

		if(input.contains("QUIT") || input.contains("EXIT")){ 
            // exit and quit are in the input
			type = MoveType.QUIT;
			arg = input.substring(4);
        }
        else if(input.contains("LOOK") || input.equals("") || input.equals("DISPLAY") ){ 
            // if input line has look the print the room (can inhance 
            // with specific look later)
            type = MoveType.DISPLAY;
        }
        else if(input.contains("GO ")){ 
            // update the current room with the result of following 
            // user specified direction
            type = MoveType.GO;
            arg = input.substring(3);
        }
        else if(input.contains("GET ")){
            type = MoveType.GET;
            arg = input.substring(4);

        }
        else if(input.contains("DROP ")){
            type = MoveType.GET;
            arg = input.substring(5);

        }
        else if(input.contains("USE ")){
            type = MoveType.USE;
            arg = input.substring(4);

		}
		else if(input.contains("CRAFT ")){
            type = MoveType.CRAFT;
            arg = input.substring(6);
        }
        else if(input.contains("INVENTORY") || input.contains("INVE")){
            type = MoveType.INVE;

        }
        else{
            type = MoveType.GO;
            arg = input;

        }		
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

		//System.out.println("--" + mv.name());

		switch(mv) {

			case GO:
				
				charLoc.removeCharacter(c);
				Place tempCurr = charLoc;

				if(c instanceof NPC && ( !charLoc.followDirection(arg, c).isExit() ) ){
					tempCurr = charLoc.followDirection(arg, c);
				} else {
					tempCurr = charLoc.followDirection(arg, c);
				}

				//System.out.println(tempCurr.name());

				int id = tempCurr.ID();
				c.moveToPlace(id);

				if(tempCurr == charLoc && c instanceof Player){
					//this case only happens when user input direction does not exist in this room
					//System.out.println("\nThere was either no Room in that direction or\n"
					//					+ "You entered wrong input check and Try Again.\n");
					
					c.getString("\nThere was either no Room in that direction or\n"
									+ "You entered wrong input check and Try Again.\n");

				} else {
					charLoc = tempCurr;
					//c.getString("\nYou are now in " + charLoc.name() + "\n");
				}

				charLoc.addCharacter(c);
				//this.canRepeat = false;
				break;
			
			case LOOK:
				charLoc.display(c);
				// charLoc.lookArtifact();
				//this.canRepeat = true;
				break;
			

			case GET:

				if(charLoc.hasArtifact( arg )) {
					c.addArtifact( charLoc.popItem( arg ) );
					c.getString("\n-+ Item [" + arg + "] Aquired +-\n");
				}
				//canRepeat = false;
				break;
			
			case DROP:
				Artifact a = c.popItem(arg);

				if(a != null) {
					charLoc.addArtifact(a);
					//System.out.println("\nDroped " + arg + " in " + charLoc.name());
					c.getString("\nDroped " + arg + " in " + charLoc.name());
				} else if ( a == null && c instanceof Player) {
					//System.out.println("\nYou Don't have any artifact called " + arg);
					c.getString("\nYou Don't have any artifact called " + arg);
				}

				//this.canRepeat = false;
				break;
			
			case USE:

				Artifact ab = c.selectItem(arg);

				if(ab != null) {
					ab.use(c);
				} else if (ab == null && c instanceof Player) {
					//System.out.println("\nYou Don't have any artifact named " + arg);
					c.getString("\nYou Don't have any artifact named " + arg);
				}
				break;

			case INVE: case INVENTORY:

				//this.canRepeat = true;
				c.printItems();

				try {
					Thread.sleep(1500);
				} catch (Exception e) {
					//TODO: handle exception
					e.printStackTrace();
				}

				break;

			case CRAFT:

				int index = Recipe.HasRecipe(arg);

				if(index >= 0) {
					Recipe r = Recipe.getRecipe(index);
					Artifact aa = r.MakeArtifact(c);

					//a.print();
					if(aa != null) {
						//System.out.println("\n\t~ " + aa.name() + " was crafted. It is now available in your inventory\n");
						c.getString("\n\t~ " + aa.name() + " was crafted. It is now available in your inventory\n");
					}


				} else {
					//System.out.println("Nothing");
				}

				//this.canRepeat = true;
				break;

			case EXIT: case QUIT:

				if(arg.trim().equalsIgnoreCase("All")){
					//System.out.println("\nExiting Game...\nGoodbye :-)\n\n\t~ All players left\n\n");
					c.getString("\nExiting Game...\nGoodbye :-)\n\n\t~ All players left\n\n");
					GameTester.quit();
				}

				//System.out.println("\nGoodbye " + c.name());
				c.getString("\nGoodbye " + c.name());
				//System.out.println("\t~ " + c.name() + "~  has left the game");
				c.getString("\t~ " + c.name() + "~ has left the game");

				Game.reaper(c);
				// add something to make sure all game quits with no players

				break;
			
			
			default:
				//System.out.println("\nYou couldn't do anything. Maybe try doing some else...\n");
				//c.getString("\nYou couldn't do anything. Maybe try doing some else...\n");
				//this.canRepeat = true;
		
		}
	}
}

/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  Description: Move class -- Holds the MoveType Enum that has a list of moves either a player
 *  			 or a NPC can make. The Enum has type() method which takes in a string and returns
 *  			 MoveType that it matches. Move constructor takes in a string and generates a Move
 *  			 getting the MoveType associated to it and the arguments passed through that string.
 *  			 Other than this Move also has to methods to return MoveType and agruements.
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

	public void execute(Character c, Place charLoc, Move m) {

		MoveType mv = m.getType();
		String arg = m.args();

		switch(mv) {

			case GO:
				charLoc.removeCharacter(c);
				Place tempCurr = charLoc;

				if(c instanceof NPC && ( !charLoc.followDirection(arg).isExit() ) ){
					tempCurr = charLoc.followDirection(arg);
				} else {
					tempCurr = charLoc.followDirection(arg);
				}

				if(tempCurr == charLoc && c instanceof Player){
					//this case only happens when user input direction does not exist in this room
					System.out.println("\nThere was either no Room in that direction or\n"
										+ "You entered wrong input check and Try Again.\n");
				} else {
					charLoc = tempCurr;
				}

				charLoc.addCharacter(c);
				//this.canRepeat = false;
				break;
			
			case LOOK:
				charLoc.display();
				// charLoc.lookArtifact();
				//this.canRepeat = true;
				break;
			
			/*case DISPLAY:

				if(this.hintCount == 0) {
					System.out.println("\n\nYou have used up all your hints.\nFrom here on out you must procede with caution");
					break;
				}
				
				this.hintCount--;
				this.print();
				this.charLoc.display();
				for(int i = 0; i < this.char_artf.size(); i++) {
					System.out.println("\nName: " + this.char_artf.get(i).name()
									+ "\nValue: " + this.char_artf.get(i).value()
									+ "\tMobility: " + this.char_artf.get(i).size()
									+ "\nDescription: " + this.char_artf.get(i).description());
				}

				this.canRepeat = true;
				break;*/
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
				if(index >= 0) {
					Recipe r = Recipe.getRecipe(index);
					Artifact aa = r.MakeArtifact(c);

					//a.print();
					if(aa != null) {
						System.out.println("\n\t~ " + aa.name() + " was crafted. It is now available in your inventory");
					}
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


	/* Updates the Collection of Characters in Character class and Game class.
	 * If a player character exits this will remove it from the both classes and
	 * update the internal counter to determine when to exit the game, that is,
	 * if no player characters are present the game will stop playing even if there
	 * are NPC present in the game.
	 */
	/*public void exit() {
		
		if(c instanceof Player && playable > 0) {
			playable--;
			Game.charcUpdate(c, 0);
		}

		if(playable == 0){
			System.out.println("All players have exited the game...\n\n");
			System.exit(0);
		}

		charLoc.removeCharacter(this);
		character.remove(this);
	}*/


	/* Removes character when from the personal list when a character (player or NPC)
	 * exits the game.
	 */
	/*public static void charcUpdate(Character c, int flag) {
		
		if(flag == 0){
			charc.remove(c);
		} else if(flag == 1){
			charc.add(c);
		}

		//charc.remove(c);
	}*/
}

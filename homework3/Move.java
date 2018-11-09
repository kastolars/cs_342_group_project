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
}

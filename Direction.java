/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: This class creates all the directions for a room and where they lead
 *              Has an enum with all 18 possible direcitons.
 *              --> follow (String) : follows the direction and returns the new Place
 *              --> use(key) : when a room is locked and the lock pattern matches the keypattern
 *                          unlocks the room.
 */ 


import static java.lang.Math.abs;

public class Direction {
    protected int id;
    protected DirType dir;
    protected Place source;
    protected Place destination;
    protected boolean locked;
    protected int lockPattern;

    public Direction(int id, int sourceId, String type, int destId, int lockPattern) {
        this.id = abs(id);
        this.source = Place.getPlaceById(sourceId);
        this.dir = DirType.valueOf(type);
        this.destination = Place.getPlaceById(abs(destId));
        this.locked = destId < 0;
        this.lockPattern = abs(lockPattern);

        // System.out.println(sourceId);

        Place.getPlaceById(sourceId).addDirection(this);
    }

    public boolean match(String s) {
        return dir.match(s);
    }

    //follows the direction and if locked notifies
    public Place follow(String s) throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!");
        } else {
            return destination;
        }
    }


    //uses the key passed in to match the lock patterns and if they match unlocks
    //the direction
    public void useKey(Artifact a, Character c) {
        if(a.pattern() == lockPattern) {
			ID = ID * -1;
			
			if(ID > 0) {
				System.out.println("Someone Unlocked a Room. Hurry before it gets locked again...\n");
			} else {
				System.out.println("Someone Locked a Room. Choose your path carefully...\n");
			}
		}
    }

    //Contains all 18 directions 
    protected enum DirType {
        NONE("None", "None"),
        N("North", "N"),
        S("South", "S"),
        E("East", "E"),
        W("West", "W"),
        U("Up", "U"),
        D("Down", "D"),
        NE("Northeast", "NE"),
        NW("Northwest", "NW"),
        SE("Southeast", "SE"),
        SW("Southwest", "SW"),
        NNE("North-Northeast", "NNE"),
        NNW("North-Northwest", "NNW"),
        ENE("East-Northeast", "ENE"),
        WNW("West-Northwest", "WNW"),
        ESE("East-Southeast", "ESE"),
        WSW("West-Southwest", "WSW"),
        SSE("South-Southeast", "SSE"),
        SSW("South-Southwest", "SSW");

        protected final String text;
        protected final String abbreviation;

        DirType(String text, String abbreviation) {
            this.text = text;
            this.abbreviation = abbreviation;
        }

        protected boolean match(String s){
            return s.matches("(?i)" + text + "|" + abbreviation);
        }
    }

    public class LockedDirectionException extends Throwable {
        public LockedDirectionException(String s) {
                System.out.println(s);
        }
    }

    public String type(){
        return this.dir.abbreviation;
    }
}

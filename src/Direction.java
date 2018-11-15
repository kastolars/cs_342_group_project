import static java.lang.Math.abs;

public class Direction {
    private int id;
    private DirType dir;
    private Place source;
    private Place destination;
    private boolean locked;
    private int lockPattern;

    public Direction(int id, int sourceId, String type, int destId, int lockPattern) {
        this.id = abs(id);
        this.source = Place.getPlaceById(sourceId);
        this.dir = DirType.valueOf(type);
        this.destination = Place.getPlaceById(abs(destId));
        this.locked = destId < 0;
        this.lockPattern = abs(lockPattern);
    }

    public boolean match(String s) {
        return dir.match(s);
    }

    public Place follow(String s) throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!");
        } else {
            return destination;
        }
    }

    private enum DirType {
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

        private final String text;
        private final String abbreviation;

        DirType(String text, String abbreviation) {
            this.text = text;
            this.abbreviation = abbreviation;
        }

        private boolean match(String s){
            return s.matches("(?i)" + text + "|" + abbreviation);
        }
    }

    public class LockedDirectionException extends Throwable {
        public LockedDirectionException(String s) {
                System.out.println(s);
        }
    }
}

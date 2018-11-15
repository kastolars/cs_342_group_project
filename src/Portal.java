import java.util.HashSet;
import java.util.Scanner;

public class Portal extends Direction {
    // private static HashSet<Direction> portals = new HashSet<Direction>();
    private boolean activated;

    public Portal(int id, int sourceId, String type, int destId, int lockPattern) {
        super(id, sourceId, type, destId, lockPattern);
        // portals.add(this);
        activated = false;
    }

    @Override
    public Place follow(String s) throws LockedDirectionException {
        if (activated){
            super.follow(s);
        }
        return Place.getStart();
//        Scanner sc = KeyboardScanner.getKeyBoardScanner();
//        int choice;
//        do {
//            System.out.println("This is a portal.");
//            System.out.println("Select a destination to port to:");
//            System.out.println("\t1. Starting location.");
//            System.out.println("\t2. Adjacent location.");
//            System.out.println("\t3. Random location.");
//            choice = sc.nextInt();
//        } while ((choice > 3) || (choice < 1));
    }
}

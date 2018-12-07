/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Direction to allow for instant travel from one place
 *              to another. When passed through the portal the character is
 *              teleported to Enterance Hall or the first place in the GDF
 *              file.
 */

public class Portal extends Direction {
    // private static HashSet<Direction> portals = new HashSet<Direction>();
    private boolean activated;

    public Portal(int id, int sourceId, String type, int destId, int lockPattern) {
        super(id, sourceId, type, destId, lockPattern);
        // portals.add(this);
        // System.out.println( "the cake is a lie" );
        activated = false;
    }

    @Override
    public Place follow(Character c, String s) throws LockedDirectionException {
        if (activated){
            super.follow(c, s);
        }
//        System.out.println("\nThere and back again\n");
        c.getString("\nThere and back again\n");
        //c.getString("\nThere and back again\n");
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

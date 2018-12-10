/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: This class extends Directions to make a direction boobytrapped and will cause damage
 *              to everyone in that room if a character decides to go in that direction.
 *              This function overrides follow in Direction class to do damage on each person
 *              in the room and then calls follow in that direction.
 */ 


public class BoobyTrappedDirection extends Direction {


    //initiallizes the direction with boobytrap
    public BoobyTrappedDirection(int id, int sourceId, String type, int destId, int lockPattern) {
        super(id, sourceId, type, destId, lockPattern);
        // System.out.println( "Glados" );
    }

    //overrides the current follow function to do damage on all the characters 
    //in that room and then follows in that direction
    @Override
    public Place follow(Character c, String s) throws LockedDirectionException {
        
        source.getCharacters().forEach(x -> x.takeDamage(20));
//        System.out.println("Oh no! A boom went off\n");
        for (Character x: this.source.characters
             ) {
            x.getString("Oh no! A boom went off\n");
        }
        return super.follow(c, s);
    }

}

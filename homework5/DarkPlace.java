/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends Place, this place is dark and any character in this room can't
 *              do anything until they "illuminate" the room using a torch. Upon using
 *              the torch the description of the room changes to indicate the room
 *              has been enlightened.
 */ 


public class DarkPlace extends Place{

    private final String darkName;
    private final String darkDescription;
    private boolean dark;

    public DarkPlace(int id, String name, String description) {
        super(id, name, description);
        darkName = "Dark Place";
        darkDescription = "You can't see a thing in here!";
        dark = true;
    }

    //sets dark to false to indicate a torch was used
    @Override
    public void illuminate(){
        dark = false;
    }


    //changes the description of the room to indicate end of darkness
    @Override
    public void display(Character c) {
        if (!dark){
            super.display(c);
        } else {
            //System.out.println(darkName);
            c.getString(darkName);
            //System.out.println(darkDescription);
            c.getString(darkDescription);
        }
    }
}

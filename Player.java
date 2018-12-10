import java.util.Scanner;

/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 5: Group Project
 * 
 * DESCRIPTION:
 *  This class outlines the behavior of the Players
 *  EXTENDS Character
 *  UI and AI are handled here by implementing DecisionMaker
 *  print() : void -- print the debuging info
 *  display() : void -- pretty print the info
 *  makeMove() : void -- decide on a move to make
 *  getByID() : Character -- static collection of characters
 *  Character(ID, name, desc) : Character -- make a character directly
 *  Character(Scanner) : Character -- have a character make themself from GDF
 *  
 *  getMove() : Move -- create a new move and return that
 *  makeMove() : void -- call get move and then executes it
 */

public class Player extends Character implements DecisionMaker{

    private IO user;

    public Player(Scanner s){
        super(s);
        health = 100;
        mana = 100;
        lives = 3;

        user = new IO();
    }

    public Player(int i, String s, String d){
        super(i, s, d);
        health = 100;
        mana = 100;
        lives = 3;

        //System.out.printf(" # %d, %d, %d # ", health, mana, lives);

        user = new IO();
    }

    public void getString(String s) {
        //System.out.println(s);
        user.display(s);
    }

    public Move getMove() {

        getCurrentPlace().display(this);
        //System.out.print("\n" + name() + " -> ");
        getString("\n" + name() + " -> ");
        
        String s = user.getline();
        //System.out.println ("Player: " + s);
        
        return new Move(s);
    }

    public void makeMove(){
        //System.out.print(color);
        this.getString(color);

        toggleGUI(true);
        getMove()
            .execute( this, Place.getPlaceById(placeID) );
        //System.out.print(CColor.RESET);
        this.getString(CColor.RESET);

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        toggleGUI(false);
    }

    protected void lifeCheck(){
        lives--;
        health = 100;
        mana = 100;
    }

    public void cast(String spell, Place p){
        //System.out.println("  ... nothing happened ... ");
        this.getString(" ... nothing happened ... ");
    }

    @Override
    public void toggleGUI(Boolean tog){

        //System.out.println("Attempting Toggle..." + tog);
        user.setVisibility(tog);
    }

}

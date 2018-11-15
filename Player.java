import java.util.Scanner;

/**
 * AUTHOR: lpaltz2 -- hw3
 * DATE: October 25, 2018
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
    public Player(Scanner s){
        super(s);
    }

    public Player(int i, String s, String d){
        super(i, s, d);
        health = 100;
        mana = 100;
        lives = 3;
    }

    public Move getMove(Character actor, Place room){
        return new Move(actor, room);
    }

    public void makeMove(){
        System.out.print(color);

        getMove( this, Place.getPlaceByID(placeID) )
            .execute();

        System.out.print(CColor.RESET);
    }

    protected void lifeCheck(){
        lives--;
        health = 100;
        mana = 100;
    }

    public void cast(String spell, Place p){
        System.out.println("  ... nothing happened ... ");
    }
}
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;

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

public class NPC extends Character implements DecisionMaker{
    public NPC(Scanner s){
        super(s);
        health = 100;
        mana = 0;
        lives = 1;
    }

    public NPC(int i, String s, String d){
        super(i, s, d);
        health = 100;
        mana = 0;
        lives = 1;
    }
/*
    public Move getMove(Character actor, Place room){
        return new Move( actor, room );
    }
    public void makeMove(){
        PrintStream tmp = System.out;

        System.setOut( new PrintStream(
            new OutputStream(){
                @Override
                public void write(int b) throws IOException {

                }
            } )
        );

        System.out.print(CColor.RED);

        getMove( this, Place.getPlaceByID(placeID) )
            .execute(); 

        System.out.print(CColor.RESET);

        System.setOut( tmp );
    }

    public void cast(String spell, Place p){
        System.out.println("  ... nothing happened ... ");
    }

    protected void lifeCheck(){
        lives--;
        health = 100;
        mana = 0;
    }
*/
}

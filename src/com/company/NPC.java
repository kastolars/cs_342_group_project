import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
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

    public Move getMove(){
        // AI move
        ArrayList<Move.MoveType> moves = new ArrayList<Move.MoveType>(
                                    Arrays.asList(  Move.MoveType.GO, 
                                                    Move.MoveType.GET, 
                                                    Move.MoveType.DROP, 
                                                    Move.MoveType.USE,
                                                    Move.MoveType.STAY )
                                    );

        Collections.shuffle( moves );

        Move.MoveType move = moves.get(0);
        String args = "";

        if( move.equals( Move.MoveType.GO ) )
            args = getCurrentPlace().getRandomDirection();
        else if( move.equals( Move.MoveType.GET ) )
            args = getCurrentPlace().randItem();
        else if( move.equals( Move.MoveType.USE ) )
            args = randItem();
        else if( move.equals( Move.MoveType.DROP ) )
            args = randItem();
        else
            args = "";

        return new Move( move.toString() + " " + args );
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

        getMove()
            .execute( this, Place.getPlaceById(placeID) ); 

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

}

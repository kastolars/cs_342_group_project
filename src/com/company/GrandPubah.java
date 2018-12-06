/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends the NPC class initialize essential data for the
 *              character like health, lives etc.
 */ 

import java.util.Scanner;

public class GrandPubah extends NPC{
    public GrandPubah(Scanner s){
        super(s);
        health = 1000;
        mana = 100;
        lives = 5;
    }

    public GrandPubah(int i, String s, String d){
        super(i, s, d);
        health = 1000;
        mana = 100;
        lives = 5;
    }

    protected void lifeCheck(){
        lives--;
        health = 1000;
        mana = 100;
    }

    public void cast(String spell, Place p){
        useMana();
        if( !spell.equalsIgnoreCase("skip") ){
            
        }
    }

}

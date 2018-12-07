

/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Extends the NPC class initialize essential data for the
 *              character like health, lives etc.
 */


import java.util.Scanner;

public class Ogre extends NPC{

    public Ogre(Scanner s){
        super(s);
        health = 200;
        mana = 0;
        lives = 2;
    }

    public Ogre(int i, String s, String d){
        super(i, s, d);
        health = 1000;
        mana = 100;
        lives = 5;
    }

    protected void lifeCheck(){
        lives--;
        health = 200;
        mana = 0;
    }

    public void cast(String spell, Place p){
        //System.out.println("  ... nothing happened ... ");
        this.getString(" ... nothing happened ... ");
    }

}

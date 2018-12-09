/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 5: Group Project -- GUI
 * Description: Input/Output selector, will randomly assign an IO to the
 *              Character.
 */ 


import java.awt.Frame;
import javax.swing.*;
import java.util.Random;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;

    private int chosenInterface;
    private UserInterface user;

    private Boolean isTurn;

    private String inputBuffer;

    public IO() {

        Random rand = new Random();

        chosenInterface =  rand.nextInt(3);
        isTurn = false;
        System.out.println(chosenInterface);
        selectInterface(chosenInterface);

    }

    public void setVisibility (Boolean turn) {
        isTurn = turn;
        user.frameUpdate(turn);
    }

    public Boolean visibility() {
        return isTurn;
    }


    public String getBuffer() {

        return this.inputBuffer;
    }


    public void display(String s) {

        user.display(s);

    }

    public String getline() {
        
        return user.getLine();
    
    }

    
    public void selectInterface(int i){
        
        chosenInterface = i;

        switch (chosenInterface) {

        case 1:
            //select GUI_1
            user = new GUI_1(this);
            break;


        case 2:
            //select GUI_2
            user = new GUI_2(this);
            break;
        
        case 3:
            //select GUI_3
            user = new GUI_3();
            break;

        default:
            //select TextInterface
            user = new TextInterface();

        }


    }
}

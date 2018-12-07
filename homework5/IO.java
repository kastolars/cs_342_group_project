
//package com.company;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;

    private int chosenInterface;
    private UserInterface user;

    private Character player;

    private Boolean isTurn = true;

    private String inputBuffer;

    public IO(Character c) {
        //chosenInterface = 0;
        player = c;
        selectInterface(2);
    }

    public void setVisibility (Boolean turn) {
        isTurn = turn;
    }


    public String getBuffer() {
        //System.out.println(inputBuffer);
        //System.out.println(inputBuffer);
        return this.inputBuffer;
    }


    public void display(String s) {

        //System.out.println(s);
        
        if(chosenInterface > 0) {
            
            /*if(inputBuffer == null) {
                inputBuffer = s;
            } else {
                inputBuffer += s;
            }*/

            user.display(s);

        } else if (chosenInterface == 0) {
            user.display(s);
        }

    }

    public String getline() {
        
        user = new TextInterface();
        return user.getLine();
    
    }

    
    public void selectInterface(int i){
        chosenInterface = i;

        switch (chosenInterface) {

        case 1:
            //select GUI_1
            user = new GUI_1();
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

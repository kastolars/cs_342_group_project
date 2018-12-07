//package com.company;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;

    private int chosenInterface;
    private UserInterface user;

    public IO() {
        //chosenInterface = 0;
        selectInterface(0);
    }

    public void display(String s) {
        
        user.display(s);

    }

    public String getline(String s){return "";}

    
    public void selectInterface(int i){
        chosenInterface = i;

        switch (chosenInterface) {

        case 1:
            //select GUI_1
            break;


        case 2:
            //select GUI_2
            break;
        
        case 3:
            //select GUI_3
            break;

        default:
            //select TextInterface
            user = new TextInterface();

        }


    }
}

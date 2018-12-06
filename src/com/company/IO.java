package com.company;

public class IO {

    public static final int TEXT = 0;
    public static final int GUI_1 = 1;
    public static final int GUI_2 = 2;
    public static final int GUI_3 = 3;

    private int chosenInterface;

    public IO() {
        chosenInterface = 0;
    }

    public void display(String s){}

    public String getline(String s){return "";}

    public void selectInterface(int i){
        chosenInterface = i;
    }
}

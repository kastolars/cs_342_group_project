//package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_3 extends JFrame implements UserInterface {

    private Text_Pane_1 textPanel;
    private Toolbar_3 top;
    private IO user;

    public GUI_3 (IO intF) {
        super("GUI 3 -- Luke Paltzer");

        user = intF;
        setLayout(new BorderLayout());

        top = new Toolbar_3();
        textPanel = new Text_Pane_1();

        top.setTextPanel(textPanel);

        add(top, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        //printOnGUI();

        setSize(700, 500);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(user.visibility());
    }

    @Override
    public void display( String s ) {
        textPanel.appendText( s );
    }

    @Override
    public String getLine() {
        top.set();

        while( !top.ready() ) {
            
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        };

        if(top.ready()) {
            return top.getLine();
        }
        else {
            return "Stay";
        }
    }


    @Override
    public void frameUpdate(Boolean b) {
        setVisible(b);
        textPanel.flush();
    }
}

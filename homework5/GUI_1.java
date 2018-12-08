//package com.company;
import javax.swing.*;
import java.awt.*;

public class GUI_1 extends JFrame implements UserInterface{


    private TextPanel textPanel;
    private Toolbar_1 toolbar;
    private BottomToolbar bottomToolbar;

    private String outBuffer;

    private IO user;

    public GUI_1(IO intF) throws HeadlessException {

        super("GUI 1");

        user = intF;
        setLayout(new BorderLayout());

        toolbar = new Toolbar_1();
        bottomToolbar = new BottomToolbar();
        textPanel = new TextPanel();

        toolbar.setTextPanel(textPanel);
        bottomToolbar.setTextPanel(textPanel);

        add(toolbar, BorderLayout.NORTH);
        add(bottomToolbar, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        printOnGUI();

        setSize(500, 400);
        setDefaultCloseOperation(3);
        setVisible(user.visibility());
    }
    

    public void printOnGUI() {
        textPanel.appendText(outBuffer);
    }
    
    @Override
    public void display(String p) {

        if(outBuffer == null) {
            outBuffer = p;
        } else {
            outBuffer += p;
        }

        //printOnGUI();
        //textPanel.appendText(outBuffer);

    }

    @Override
    public String getLine() {
        return KeyboardScanner.getKeyboardScanner().nextLine();
    }

    @Override
    public void frameUpdate(Boolean b) {
        
        setVisible(b);
        
        textPanel.flush();
        outBuffer = "";
    }
}

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

        super("GUI 1 -- Karol Stolarski");

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

        //printOnGUI();

        setSize(500, 400);
        setDefaultCloseOperation(3);
        setVisible(user.visibility());
    }
    

    public void printOnGUI(String s) {
        textPanel.appendText(s);
    }
    
    @Override
    public void display(String p) {

        System.out.println(p);
        printOnGUI(p);

    }

    @Override
    public String getLine() {

        toolbar.setReady();
        bottomToolbar.setReady();

        while( !toolbar.getReady() && !bottomToolbar.getReady() ) {
            
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        };

        //System.out.println("END LOOP");
        if(toolbar.getReady()) {
            return toolbar.getLine();
        } else if(bottomToolbar.getReady()) {
            System.out.println(bottomToolbar.getLine());
            return bottomToolbar.getLine();
        } else {
            return "Stay";
        }
        
    }

    @Override
    public void frameUpdate(Boolean b) {
        
        setVisible(b);
        
        textPanel.flush();
        //outBuffer = "";
    }
}

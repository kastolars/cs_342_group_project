//package com.company;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.Dimension;

public class GUI_2 extends JFrame implements UserInterface {
    
    private MovePane_2 movePanel;
    private MovePane_2 optionPanel;
    private TextPane_2 textPanel;
    private Toolbar toolbar;

    private String outBuffer;

    private Boolean visibility = false;
    private IO user;

    public GUI_2 (IO intF) {

        System.out.println("Broke Here");

        //visibility = user.visibility();

        user = intF;

        //new MainFrame_2();
        movePanel = new MovePane_2("Select Move");
        movePanel.moveButton();

        //optionPanel = new MovePane_2("Available Options");
        //optionPanel.optionButton();

        textPanel = new TextPane_2();
        movePanel.setPanel(textPanel);

        toolbar = new Toolbar();

        toolbar.setGUIPrinter(new PrintOnGUI() {
            @Override
            public void print(Boolean canPrint) {

                if(canPrint) {
                    textPanel.appendText(outBuffer);
                    System.out.println(outBuffer);
                    setVisibility(canPrint);
                }
            }
        });

        //System.out.println("Broke Here");

        add(textPanel, BorderLayout.SOUTH);
        add(movePanel, BorderLayout.CENTER);
        //add(optionPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        printOnGUI();

        //System.out.println("Broke Here");

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //System.out.println( " set V " + user.visibility());
        this.setVisible(user.visibility());
        //setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    @Override
    public void frameUpdate(Boolean turn) {

        //System.out.println("Updated " + turn);
        //repaint();

        setVisible(turn);
        
        /*if(turn) {
            //setState(Frame.NORMAL);
            setVisible(true);
        } else {
            //setState(Frame.ICONIFIED);
            setVisible(false);
        }*/

        //textPanel.repaint();
        //textPanel.revalidate();
        textPanel.flush();
        outBuffer = "";
        //revalidate();
        
        

    }

    public void setVisibility(Boolean isVis) {
        setVisible(isVis);
    }

    public void printOnGUI() {

        if( toolbar.isChoosen() ) {
            textPanel.appendText(outBuffer);
            System.out.println(outBuffer);
        }
    }
    
    @Override
    public void display(String p) {
        
        //System.out.println(p);

        if(outBuffer == null) {
            outBuffer = p;
        } else {
            outBuffer += p;
        }

    }

    @Override
    public String getLine() {
        System.out.println("I was called");

        while( !movePanel.pieceOfShit() ) {
            
            try {
                Thread.sleep(250);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        movePanel.pieceOfShit2();

        System.out.println("End loop");
        return movePanel.getLine();
        //return KeyboardScanner.getKeyboardScanner().nextLine();
    }
}

/* JFrame frame = new JFrame("Ayush GUI");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

Container split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

Container pane = frame.getContentPane();
//panel.setLayout(new GridLayout(3, 1));
pane.setLayout(new GridBagLayout());

GridBagConstraints gbc = new GridBagConstraints();
gbc.fill = GridBagConstraints.HORIZONTAL;

JButton button = new JButton("GO");
gbc.weightx = 0.5;
gbc.fill = GridBagConstraints.HORIZONTAL;
gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 1;
//button.setPreferredSize(new Dimension(20, 20));

pane.add(button, gbc);

button = new JButton("DROP");
gbc.fill = GridBagConstraints.HORIZONTAL;
gbc.weightx = 0.5;
gbc.gridx = 1;
gbc.gridy = 0;
gbc.gridwidth = 1;
pane.add(button, gbc);


frame.pack();
frame.setSize(500, 500);
frame.setVisible(true);
 */



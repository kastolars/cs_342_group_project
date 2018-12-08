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
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI_2 extends JFrame implements UserInterface {
    
    private MovePane_2 movePanel;
    //private MovePane_2 optionPanel;
    private TextPane_2 textPanel;
    private Toolbar toolbar;

    private JButton okBtn;
    private JPanel okPanel;
    private JTextField okText;

    private String outBuffer;

    private Boolean visibility = false;
    private IO user;

    private Boolean turnOver;

    public GUI_2 (IO intF) {

        //System.out.println("Broke Here");

        //visibility = user.visibility();

        super("GUI 2 -- Ayush Patel");

        user = intF;
        turnOver = false;

        /*
        okBtn = new JButton("OK");
        okPanel = new JPanel();
        okText = new JTextField(10);

        okBtn.addActionListener(this);

        Border innerBorder = (BorderFactory.createTitledBorder("Enter Option and Press Okay"));
        Border outterBorder = (BorderFactory.createEmptyBorder(100,10,100,5));
        okPanel.setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

        okPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        okPanel.add(okText);
        okPanel.add(okBtn);
        */


        //optionPanel = new MovePane_2("Available Options");
        //optionPanel.optionButton();

        movePanel = new MovePane_2("Select Move");
        movePanel.moveButton();

        textPanel = new TextPane_2();
        movePanel.setPanel(textPanel);

        toolbar = new Toolbar();

        /*toolbar.setGUIPrinter(new PrintOnGUI() {
            @Override
            public void print(Boolean canPrint) {

                if(canPrint) {
                    textPanel.appendText(outBuffer);
                    System.out.println(outBuffer);
                    setVisibility(canPrint);
                }
            }
        });*/

        //System.out.println("Broke Here");

        add(textPanel, BorderLayout.SOUTH);
        add(movePanel, BorderLayout.CENTER);
        //add(okPanel, BorderLayout.CENTER);
        //add(okBtn, BorderLayout.CENTER);
        //add(optionPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        //printOnGUI();
        //System.out.println("Broke Here");

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void printOnGUI(String s) {

        textPanel.appendText(s);
        /*if( toolbar.isChoosen() ) {
            textPanel.appendText(outBuffer);
            System.out.println(outBuffer);
        }*/
    }
    
    @Override
    public void display(String p) {
        
        System.out.println(p);

        if(!turnOver) {
            printOnGUI(p);
        }

        /*if(outBuffer == null) {
            outBuffer = p;
        } else {
            outBuffer += p;
        }*/

    }

    @Override
    public String getLine() {
        //System.out.println("I was called");
        movePanel.pieceOfShit2();

        while( !movePanel.pieceOfShit() ) {
            
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        turnOver = true;
        //System.out.println("End loop");
        return movePanel.getLine();
        //return KeyboardScanner.getKeyboardScanner().nextLine();
    }
}

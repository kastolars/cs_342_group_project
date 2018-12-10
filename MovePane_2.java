/* Name: Ayush Patel
 * Group: 34
 * Homework 5: Group Project -- GUI
 * Description: GUI to display outputs and perform moves using Buttons
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;

public class MovePane_2 extends JPanel implements ActionListener {

    //MOVE MENU BUTTONS
    private JButton go;
    private JButton get;
    private JButton drop;
    private JButton craft;
    private JButton inve;
    private JButton use;
    private JButton exit;
    private JButton look;

    private JComboBox downs;

    private Boolean isReady;


    //OPTION MENU TEXTBOX
    private JTextField textField;
    private JButton ok;

    private static String buffer = "";

    private TextPane_2 panel;

    public MovePane_2 (String name) {

        Dimension dim = getPreferredSize();
        isReady = false;

        textField = new JTextField(10);
        textField.setSize(10, 10);
        ok = new JButton("OK");

        Border innerBorder = (BorderFactory.createTitledBorder(name));
        Border outterBorder = (BorderFactory.createEmptyBorder());
        setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

    }

    /*
    public void moveButton () {

        go = new JButton("Go");             go.addActionListener(this);
        get = new JButton("Get");           get.addActionListener(this);
        drop = new JButton("Drop");         drop.addActionListener(this);
        craft = new JButton("Craft");       craft.addActionListener(this);
        inve = new JButton("Inventory");    inve.addActionListener(this);
        use = new JButton("Use");           use.addActionListener(this);
        look = new JButton("Look");         look.addActionListener(this);
        exit = new JButton("Exit");         exit.addActionListener(this);


        setLayout(new FlowLayout(FlowLayout.NONE.NONE));

        add(go);
        add(get);
        add(drop);
        add(craft);
        add(use);

        add(look);
        add(inve);
        add(exit);

    } 
    */

    public void moveButton () {

        go = new JButton("Go");             go.addActionListener(this);
        get = new JButton("Get");           get.addActionListener(this);
        drop = new JButton("Drop");         drop.addActionListener(this);
        craft = new JButton("Craft");       craft.addActionListener(this);
        inve = new JButton("Inventory");    inve.addActionListener(this);
        use = new JButton("Use");           use.addActionListener(this);
        look = new JButton("Look");         look.addActionListener(this);
        exit = new JButton("Exit");         exit.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //gc.insets = new Insets(10, 50, 20, 0);

        //GO BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(go, gc);

        //GET BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(get, gc);

        //DROP BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(drop, gc);

        //CRAFT BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(craft, gc);

        //USE BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(use, gc);

        //LOOK BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(look, gc);

        //INVE BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 6;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(inve, gc);

        //GO BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 7;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.LINE_START;
        add(exit, gc);


        //gc.weightx = 1;
        //gc.weighty = 0.1;

        gc.insets = new Insets(0, 50, 0, 0);
        
        gc.gridx = 250;
        gc.gridy = 3;
        add(textField, gc);

        //textField.setBorder


        gc.gridx = 250;
        gc.gridy = 4;
        add(ok, gc);


        //downs = new JComboBox();
        ok.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                //buffer += ((JButton)e.getSource()).getName();
                buffer += " " + textField.getText();
                textField.setText("");
                textField.setEditable(false);

                panel.appendText(buffer + "\n");

                isReady = true;

            }
        }) ;
        
        textField.setEditable(false);

    }

    public Boolean pieceOfShit() {
        return isReady;
    }

    public void pieceOfShit2() {
        isReady = false;
    }

    public String getLine() {
        //System.out.println("Buf: " + buffer);
        return buffer;
    }


    public void setPanel (TextPane_2 panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        //System.out.println(textField);
        buffer = "";
        if(clicked == go) {
            buffer = "Go";
            textField.setEditable(true);
        } else if (clicked == look) {
            buffer = "Look";
            textField.setEditable(false);
        } else if (clicked == get) {
            buffer = "Get";
            textField.setEditable(true);
        } else if (clicked == drop) {
            buffer = "Drop";
            textField.setEditable(true);
        } else if (clicked == use) {
            buffer = "Use";
            textField.setEditable(true);
        } else if (clicked == inve) {
            buffer = "Inventory";
            textField.setEditable(false);
        } else if (clicked == craft) {
            buffer = "Craft";
            textField.setEditable(true);
        } else if (clicked == exit) {
            buffer = "Exit";
            textField.setEditable(true);
        }

    }


}
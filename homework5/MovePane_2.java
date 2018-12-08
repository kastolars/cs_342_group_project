import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
        //dim.width = 300;
        //dim.height = 250;
        //setPreferredSize(dim);

        textField = new JTextField(10);
        ok = new JButton("OK");

        Border innerBorder = (BorderFactory.createTitledBorder(name));
        Border outterBorder = (BorderFactory.createEmptyBorder());
        setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

    }

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

        //GO BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(go, gc);

        //LOOK BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(look, gc);

        //GET BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(get, gc);

        //DROP BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(drop, gc);

        //CRAFT BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(craft, gc);

        //USE BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(use, gc);

        //INVE BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 6;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(inve, gc);

        //GO BUTTON
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 7;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(exit, gc);


        //gc.weightx = 1;
        //gc.weighty = 0.1;
        gc.gridx = 250;
        gc.gridy = 150;
        add(textField, gc);


        gc.gridx = 250;
        gc.gridy = 200;
        add(ok, gc);


        //downs = new JComboBox();
        ok.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //buffer += ((JButton)e.getSource()).getName();
                buffer += " " + textField.getText();
                textField.setText("");
                textField.setEditable(false);

                buffer += "\n";
                panel.appendText(buffer);
                System.out.println("R: " + buffer + " " + isReady );
                //notify();

                isReady = true;

            }
        }) ;


        //ok.addActionListener(this);


        //setLayout(new BorderLayout());

        //textField.setText("HERE");

        //add(textField, BorderLayout.CENTER);
        //add(ok, BorderLayout.SOUTH);

        textField.setEditable(false);

    }

    public Boolean pieceOfShit() {
        //System.out.println("called..");
        //int i = 1;
        //i++;
        return isReady;
    }

    public void pieceOfShit2() {
        isReady = false;
    }

    public String getLine() {
        System.out.println("Buf: " + buffer);
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
        } else if (clicked == craft) {
            buffer = "Craft";
            textField.setEditable(true);
        /*} else if(clicked == ok && buffer.length() > 0) {
            buffer += " " + textField.getText();*/
        } else if (clicked == exit) {
            buffer = "Exit";
            textField.setEditable(true);
        }

        //buffer += "\n";
        //System.out.println("L: " + buffer);
        //panel.appendText(buffer);
    }


}
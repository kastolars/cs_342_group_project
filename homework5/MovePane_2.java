import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
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


    //OPTION MENU TEXTBOX
    private JTextField textField;
    private JButton ok;

    private static String buffer;

    private TextPane_2 panel;

    public MovePane_2 (String name) {

        Dimension dim = getPreferredSize();
        dim.width = 300;
        dim.height = 250;
        setPreferredSize(dim);

        Border innerBorder = (BorderFactory.createTitledBorder(name));
        Border outterBorder = (BorderFactory.createEmptyBorder(5,5,5,5));
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

    }

    public void optionButton() {
        
        textField = new JTextField(10);
        ok = new JButton("OK");
        ok.addActionListener(this);

        setLayout(new BorderLayout());

        add(textField, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
    }


    public void setPanel (TextPane_2 panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if(clicked == go) {
            buffer = "Go";
        } else if (clicked == look) {
            buffer = "Look";
        } else if (clicked == get) {
            buffer = "Get";
        } else if (clicked == drop) {
            buffer = "Drop";
        } else if (clicked == use) {
            buffer = "Use";
        } else if (clicked == inve) {
            buffer = "Inventory";
        } else if (clicked == craft) {
            buffer = "Craft";
        } else if(clicked == ok && buffer.length() > 0) {
            buffer += " " + textField.getText();
        } else if (clicked == exit) {
            buffer = "Exit";
        }

        buffer += "\n";
        panel.appendText(buffer);
    }


}
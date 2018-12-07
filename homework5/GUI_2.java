//package com.company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.Dimension;

public class GUI_2 implements UserInterface {
    
    public GUI_2 () {

        JFrame frame = new JFrame("Ayush GUI");
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
    }
    
    
    @Override
    public void display(String p) {

    }

    @Override
    public String getLine() {
        return "";
    }
}

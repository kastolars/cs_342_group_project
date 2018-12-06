import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.Dimension;


public class GUI_apate {

    public GUI_apate (Place p, Character c) {
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
        //frame.setLayout(new FlowLayout(FlowLayout.LEFT));
    }


    /*public void NotInUSE() {

        //JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));

        JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(new Bo)
        //FlowLayout flow = new FlowLayout(FlowLayout.LEADING, 10, 10);

        TextArea t = new JTextArea();
        t.setBounds(100, 100, 100, 20);
        t.setText("Ayush GUI");
        //frame.add(t);

        //int[] fsize = frame.getFrames();

        JLabel name = new JLabel("Name:");
        name.setBounds(100, 100, 50, 20);
        frame.add(name);

        JLabel name = new JLabel("Name: " + p.name());
        name.setVerticalAlignment(JLabel.TOP);
        name.setHorizontalAlignment(JLabel.LEFT);
        name.setLocation(10, 10);
        //name.setBounds(5, 5, 50, 20);
        panel.add(name);

        JTextArea t = new JTextArea();
        t.setText("THIS IS TEXT BOX");
        t.setBounds(100, 100, 100, 20);
        t.setText("Ayush GUI");
        panel.add(t);

        JLabel desc = new JLabel();
        desc.setText("<html>" + "Description" + "</html>");
        //desc.setVerticalAlignment(JLabel.TOP);
        //desc.setHorizontalAlignment(JLabel.LEFT);
        //desc.setBounds(10, 10, 50, 20);
        //desc.setLocation(20, 10);
        panel.add(desc);

        panel.setPreferredSize(new Dimension(500, 200));
        pane.add(panel,BorderLayout.LINE_START);
    }*/
}
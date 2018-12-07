import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Toolbar extends JPanel implements ActionListener {

    private JButton textGUI;
    private JButton gui1;
    private JButton gui2;
    private JButton gui3;

    private PrintOnGUI printer;

    private Boolean choosen = false;

    public Toolbar() {

        textGUI = new JButton("Text GUI");
        gui1 = new JButton("GUI 1");
        gui2 = new JButton("GUI 2");
        gui3 = new JButton("GUI 3");

        Border innerBorder = (BorderFactory.createTitledBorder("GUI Options"));
        Border outterBorder = (BorderFactory.createEmptyBorder(5,5,5,5));
        setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

        textGUI.addActionListener(this);
        gui1.addActionListener(this);
        gui2.addActionListener(this);
        gui3.addActionListener(this);


        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(textGUI);
        add(gui1);
        add(gui2);
        add(gui3);

    }

    public Boolean isGUI() {
        return choosen;
    }

    public void setGUIPrinter(PrintOnGUI p) {
        this.printer = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        
        if((clicked == gui2)) {
            choosen = true;
        }

        if(printer != null) {
            printer.print(choosen);
        }
        //textPanel.appendText(getBuffer());
    }

    public Boolean isChoosen() {
        return choosen;
    }

}
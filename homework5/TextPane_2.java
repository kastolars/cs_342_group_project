/* Name: Ayush Patel
 * Group: 34
 * Homework 5: Group Project -- GUI
 * Description: GUI to display outputs and perform moves using Buttons
 * 
 */

import java.awt.*;
import javax.swing.*;

public class TextPane_2 extends JPanel {

    private JTextArea textArea;

    public TextPane_2 () {

        textArea = new JTextArea();
        setLayout(new BorderLayout());

        Dimension dim = getPreferredSize();
        dim.height = 500;
        setPreferredSize(dim);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }

    public void flush() {
        textArea.setText("");
    }
}
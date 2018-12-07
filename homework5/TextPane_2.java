import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextPane_2 extends JPanel {

    private JTextArea textArea;

    public TextPane_2 () {

        textArea = new JTextArea();
        setLayout(new BorderLayout());

        Dimension dim = getPreferredSize();
        dim.height = 400;
        setPreferredSize(dim);

        add(textArea, BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}
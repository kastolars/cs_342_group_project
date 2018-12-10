import javax.swing.*;
import java.awt.*;

public class Text_Pane_1 extends JPanel {

    private JTextArea textArea;

    public Text_Pane_1() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text){
        textArea.append(text);
    }

    public void flush() {
        textArea.setText("");
    }
}
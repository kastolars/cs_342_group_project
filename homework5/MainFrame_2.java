import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.border.Border;

public class MainFrame_2 extends JFrame {

    private MovePane_2 movePanel;
    private TextPane_2 textPanel;
    private MovePane_2 optionPanel;

    public MainFrame_2 () {
        super("Ayush GUI");

        movePanel = new MovePane_2("Select Move");
        movePanel.moveButton();
        textPanel = new TextPane_2();
        optionPanel = new MovePane_2("Available Options");
        //optionPanel.optionButton();
        
        movePanel.setPanel(textPanel);

        add(textPanel, BorderLayout.SOUTH);
        add(movePanel, BorderLayout.WEST);
        add(optionPanel, BorderLayout.CENTER);

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
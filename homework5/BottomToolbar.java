import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomToolbar extends JPanel implements ActionListener {

    private JButton lookButton;
    private JButton displayButton;
    private JButton inventoryButton;
    private JButton quitButton;

    String s;

    private TextPanel textPanel;

    public BottomToolbar() {

        lookButton = new JButton("Look");
        displayButton = new JButton("Display");
        inventoryButton = new JButton("Inventory");
        quitButton = new JButton("Quit");

        lookButton.addActionListener(this);
        displayButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        quitButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(lookButton);
        add(displayButton);
        add(inventoryButton);
        add(quitButton);
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == lookButton){
            setBuffer("LOOK");
        } else if (clicked == displayButton) {
            setBuffer("DISPLAY");
        } else if (clicked == inventoryButton) {
            setBuffer("INVENTORY");
        } else if (clicked == quitButton) {
            setBuffer("QUIT");
        }
    }

    private void setBuffer(String t){
        s = t;
        textPanel.appendText(t + "\n");
    }
}
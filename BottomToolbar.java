import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomToolbar extends JPanel implements ActionListener {

    private JButton lookButton;
    private JButton displayButton;
    private JButton inventoryButton;
    private JButton quitButton;
    private JTextField textField;

    private String commandBuffer;
    private Boolean isReady;

    String s;

    private TextPanel textPanel;

    public BottomToolbar() {

        commandBuffer = "";
        isReady = false;

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

        //System.out.println("Button Clicked");

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

    public Boolean getReady() {

        return isReady;
    }

    public void setReady() {
        isReady = false;
    }

    public String getLine() {
        return commandBuffer;
    }

    private void setBuffer(String t){
        //System.out.println("SetBuffer Callled");
        s = t;
        commandBuffer = s;
        textPanel.appendText(t + "\n");
        isReady = true;
        //textField.setText("");
    }
}
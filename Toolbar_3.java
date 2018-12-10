import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar_3 extends JPanel implements ActionListener {

    private JButton goButton;
    private JButton getButton;
    private JButton dropButton;
    private JButton useButton;
    private JButton craftButton;
    private JTextField textField;
    private JButton inventoryButton;
    private JButton quitButton;

    private Boolean isReady;

    private String s; // buffer
    
    private Text_Pane_1 textPanel;

    private String commandBuffer;

    public Toolbar_3() {

        commandBuffer = "";
        isReady = false;

        goButton = new JButton("Go");
        getButton = new JButton("Get");
        dropButton = new JButton("Drop");
        useButton = new JButton("Use");
        craftButton = new JButton("Craft");
        inventoryButton = new JButton("Inventory");
        quitButton = new JButton("Quit");

        goButton.addActionListener(this);
        getButton.addActionListener(this);
        dropButton.addActionListener(this);
        useButton.addActionListener(this);
        craftButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        quitButton.addActionListener(this);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 25));

        setLayout(new GridBagLayout());

        add(goButton);
        add(getButton);
        add(dropButton);
        add(useButton);
        add(craftButton);
        add(textField);
        add(inventoryButton);
        add(quitButton);
    }

    public void setTextPanel(Text_Pane_1 textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        //System.out.println("Button Clicked");

        if (clicked == inventoryButton) {
            setBuffer("INVENTORY");
        } else if (clicked == quitButton) {
            setBuffer("QUIT");
        } else if (clicked == goButton){
            setBuffer("GO");
        } else if (clicked == getButton){
            setBuffer("GET");
        } else if (clicked == dropButton){
            setBuffer("DROP");
        } else if (clicked == useButton) {
            setBuffer("USE");
        } else if (clicked == craftButton) {
            setBuffer("CRAFT");
        }
    }

    public Boolean ready() {

        return isReady;
    }

    public void set() {
        isReady = false;
    }

    public String getLine() {
        return commandBuffer;
    }

    private void setBuffer(String command){
        String field = textField.getText();

        s = command + " " + field;
        commandBuffer = s;
        textPanel.appendText(s + "\n");
        isReady = true;
        textField.setText("");
    }
}
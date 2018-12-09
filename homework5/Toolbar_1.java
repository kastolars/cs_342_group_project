import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar_1 extends JPanel implements ActionListener {

    private JButton goButton;
    private JButton getButton;
    private JButton dropButton;
    private JButton useButton;
    private JButton craftButton;
    private JTextField textField;

    private Boolean isReady;

    private String s; // buffer

    private Boolean choosen = false;
    
    private TextPanel textPanel;

    private String commandBuffer;

    public Toolbar_1() {

        isReady = false;
        commandBuffer = "";

        goButton = new JButton("Go");
        getButton = new JButton("Get");
        dropButton = new JButton("Drop");
        useButton = new JButton("Use");
        craftButton = new JButton("Craft");
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 25));

        goButton.addActionListener(this);
        getButton.addActionListener(this);
        dropButton.addActionListener(this);
        useButton.addActionListener(this);
        craftButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(goButton);
        add(getButton);
        add(dropButton);
        add(useButton);
        add(craftButton);
        add(textField);
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == goButton){
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

    public Boolean getReady() {

        return isReady;
    }

    public void setReady() {
        isReady = false;
    }

    public String getLine() {
        return commandBuffer;
    }

    private void setBuffer(String command){
        String field = textField.getText();
        if (field.isEmpty()) {
            textPanel.appendText("I don't have enough information.\n");
        } else {
            s = command + " " + field;
            commandBuffer = s;
            textPanel.appendText(s + "\n");
            isReady = true;
            textField.setText("");
        }
    }
}
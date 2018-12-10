/* Name: Ayush Patel
 * Group: 34
 * Homework 5: Group Project -- GUI
 * Description: GUI to display outputs and perform moves using Buttons
 * 
 */


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_2 extends JFrame implements UserInterface {
    
    private MovePane_2 movePanel;
    private TextPane_2 textPanel;
    //private Toolbar toolbar;

    private JButton okBtn;
    private JPanel okPanel;
    private JTextField okText;

    private Boolean visibility = false;
    private IO user;

    private Boolean turnOver;

    public GUI_2 (IO intF) {

        super("GUI 2 -- Ayush Patel");

        user = intF;
        turnOver = false;

        movePanel = new MovePane_2("Select Move");
        movePanel.moveButton();

        textPanel = new TextPane_2();
        movePanel.setPanel(textPanel);

        //toolbar = new Toolbar();

        /*toolbar.setGUIPrinter(new PrintOnGUI() {
            @Override
            public void print(Boolean canPrint) {

                if(canPrint) {
                    textPanel.appendText(outBuffer);
                    System.out.println(outBuffer);
                    setVisibility(canPrint);
                }
            }
        });*/

        add(textPanel, BorderLayout.SOUTH);
        add(movePanel, BorderLayout.CENTER);
        //add(toolbar, BorderLayout.NORTH);

        //printOnGUI();

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(user.visibility());
        //setVisible(true);

    }

    @Override
    public void frameUpdate(Boolean turn) {

        setVisible(turn);
        textPanel.flush();

    }

    public void setVisibility(Boolean isVis) {
        setVisible(isVis);
    }


    public void printOnGUI(String s) {
        textPanel.appendText(s);
    }
    
    @Override
    public void display(String p) {
        
        System.out.println(p);
        printOnGUI(p);

    }

    @Override
    public String getLine() {

        movePanel.pieceOfShit2();

        while( !movePanel.pieceOfShit() ) {
            
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        return movePanel.getLine();

    }
}

import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Assemble_GUI_interactors extends ConsoleProgram {
    private JLabel label;
    private JTextField textField;
    private JButton button_draw_graph;
    private JButton button_clear;

    private static final int LEN_OF_JTEXTFIELD = 10;
    //private JLabel show;

    public void init() {
        label = new JLabel("Name");
        textField = new JTextField(LEN_OF_JTEXTFIELD);
        button_draw_graph = new JButton("Graph");
        button_clear = new JButton("Clear");
        add(label, SOUTH);
        add(textField, SOUTH);
        add(button_draw_graph, SOUTH);
        add(button_clear, SOUTH);
        addActionListeners();
    }
    /*
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Graph")) {
            String a_str = (String) textField.getText();
            println("Graph: \""+a_str+"\"");
        }
        else if (e.getActionCommand().equals("Clear")) {
            println("Clear");
        }
    }
}

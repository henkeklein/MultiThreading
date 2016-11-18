import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Henke on 2015-12-02.
 */
public class GuiStart extends JFrame{
    private JTextArea textArea1;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField textField1;
    private JPanel panel;

    public GuiStart() {

        super("Hej");
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Texten är sparad");
            }
        });
        setVisible(true);
    }

}

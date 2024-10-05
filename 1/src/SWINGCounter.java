import java.awt.*;
import java.awt.event.ActionEvent;
// Aditya Bhushan RA2311003010124
import java.awt.event.ActionListener;
import javax.swing.*;
public class SWINGCounter {
    private int count = 0;
    private JLabel counterLabel;
    private JTextField counterField;
    private JButton countButton;
    public SWINGCounter() {
        JFrame frame = new JFrame("SWING Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());
        counterLabel = new JLabel("Counter");
        frame.add(counterLabel);
        counterField = new JTextField(String.valueOf(count), 10);
        counterField.setEditable(false);
        frame.add(counterField);
        countButton = new JButton("Count");
        frame.add(countButton);
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                counterField.setText(String.valueOf(count));
            }
        });
        frame.setVisible(true);
    }public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SWINGCounter());
    }
}
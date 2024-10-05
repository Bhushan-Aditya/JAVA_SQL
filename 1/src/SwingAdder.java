import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class SwingAdder {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JTextField resultField;
    private JButton addButton;
    private JButton clearButton;
    public SwingAdder() {JFrame frame = new JFrame("Swing Adder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("First Number:"));
        firstNumberField = new JTextField(10);
        frame.add(firstNumberField);
        frame.add(new JLabel("Second Number:"));
        secondNumberField = new JTextField(10);
        frame.add(secondNumberField);
        frame.add(new JLabel("Result:"));
        resultField = new JTextField(10);
        resultField.setEditable(false);
        frame.add(resultField);
        addButton = new JButton("ADD");
        frame.add(addButton);
        clearButton = new JButton("CLEAR");
        frame.add(clearButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int firstNumber = Integer.parseInt(firstNumberField.getText());
                    int secondNumber = Integer.parseInt(secondNumberField.getText());
                    int sum = firstNumber + secondNumber;
                    resultField.setText(String.valueOf(sum));
                } catch (NumberFormatException ex) {
                    resultField.setText("Invalid Input");
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumberField.setText("");
                secondNumberField.setText("");resultField.setText("");
            }
        });
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingAdder());
    }
}
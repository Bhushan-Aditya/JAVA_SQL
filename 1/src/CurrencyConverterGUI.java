import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterGUI {

    private JTextField inputField;
    private JComboBox<String> fromCurrencySelector;
    private JComboBox<String> toCurrencySelector;
    private JTextField outputField;
    private JButton convertButton;

    private final double[][] exchangeRates = {
            {1.00, 1.41, 0.65, 88.21},
            {0.71, 1.00, 0.46, 62.49},
            {1.54, 2.18, 1.00, 134.62},
            {0.011, 0.016, 0.007, 1.00}
    };

    public CurrencyConverterGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        String[] currencies = {"SGD", "USD", "EUR", "INR"};
        fromCurrencySelector = new JComboBox<>(currencies);
        toCurrencySelector = new JComboBox<>(currencies);
        outputField = new JTextField(10);
        outputField.setEditable(false);
        convertButton = new JButton("Convert");

        frame.add(new JLabel("Amount:"));
        frame.add(inputField);
        frame.add(new JLabel("From:"));
        frame.add(fromCurrencySelector);
        frame.add(new JLabel("To:"));
        frame.add(toCurrencySelector);
        frame.add(convertButton);
        frame.add(new JLabel("Converted:"));
        frame.add(outputField);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    int fromIndex = fromCurrencySelector.getSelectedIndex();
                    int toIndex = toCurrencySelector.getSelectedIndex();

                    double rate = exchangeRates[fromIndex][toIndex];
                    double convertedAmount = amount * rate;

                    String fromCurrency = currencies[fromIndex];
                    String toCurrency = currencies[toIndex];
                    outputField.setText(String.format("%.2f %s", convertedAmount, toCurrency));

                } catch (NumberFormatException ex) {
                    outputField.setText("Invalid Input");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterGUI());
    }
}
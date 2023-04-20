package Views;


import DataAccess.MonthlyBillsRepository;
import Models.Customer;
import Models.MonthlyBill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBillWindow extends JFrame implements ActionListener {

    private JButton backButton, addBillButton;
    private JTextField nameField, phoneField, addressField,
            tariffField, energyRateField, meterField, energyUsedField, periodField;
    private Container contentPane;
    private final int FIELD_WIDTH = 10;
    private Customer customer;

    public AddBillWindow(Customer selectedCustomer) {

        customer = selectedCustomer;
        setTitle("KC Energy - Add Bill");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create return button
        backButton = new JButton("Return to Dashboard");
        backButton.addActionListener(this);

        // Create the create button
        addBillButton = new JButton("Add Bill");
        addBillButton.addActionListener(this);

        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(backButton, BorderLayout.NORTH);
        contentPane.add(buildBillPanel(), BorderLayout.CENTER);
        contentPane.add(addBillButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel buildBillPanel() {
        // Create name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(FIELD_WIDTH);
        nameField.setText(customer.CustomerName);
        nameField.setEditable(false);

        // Create phone field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(FIELD_WIDTH);
        phoneField.setText(customer.PhoneNumber);
        phoneField.setEditable(false);

        // Create address field
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(FIELD_WIDTH);
        addressField.setText(customer.CurrentAddress);
        addressField.setEditable(false);

        // Create tariff field
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(FIELD_WIDTH);
        tariffField.setText(Double.toString(customer.CurrentTariff));
        tariffField.setEditable(false);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(FIELD_WIDTH);
        energyRateField.setText(Double.toString(customer.CurrentEnergyRate));
        energyRateField.setEditable(false);

        // Create meter type field
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(FIELD_WIDTH);
        meterField.setText(customer.MeterType);
        meterField.setEditable(false);

        // Create energy used field
        JLabel energyUsedLabel = new JLabel("Energy Used:");
        energyUsedField = new JTextField(FIELD_WIDTH);

        // Create the billing period field
        JLabel periodLabel = new JLabel("Billing Period:");
        periodField = new JTextField(FIELD_WIDTH);

        JPanel userDisplayPanel = new JPanel();
        userDisplayPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(20, 10, 20, 10);

        // Add all components to panel //

        // row 0
        c.gridy = 0;
        c.gridx = 0;
        userDisplayPanel.add(nameLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(nameField, c);
        c.gridx = 2;
        userDisplayPanel.add(phoneLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(phoneField, c);

        // row 1
        c.gridy = 1;
        c.gridx = 0;
        userDisplayPanel.add(addressLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(addressField, c);
        c.gridx = 2;
        userDisplayPanel.add(meterLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(meterField, c);

        // row 2
        c.gridy = 2;
        c.gridx = 0;
        userDisplayPanel.add(energyRateLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(energyRateField, c);
        c.gridx = 2;
        userDisplayPanel.add(tariffLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(tariffField, c);

        // row 3
        c.gridy = 3;
        c.gridx = 0;
        userDisplayPanel.add(energyUsedLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(energyUsedField, c);
        c.gridx = 2;
        userDisplayPanel.add(periodLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(periodField, c);

        return userDisplayPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new CustomerSearchWindow();
            dispose();
        } else if (e.getSource() == addBillButton) {
            try {
                int userId = customer.CustomerId;
                double tariff = Double.parseDouble(tariffField.getText());
                double energyUsed = Double.parseDouble(energyUsedField.getText());
                double amountReceived = 0.0;
                double energyRate = Double.parseDouble(energyRateField.getText());
                double amountDue = energyUsed * energyRate * (1 + tariff);
                String meterType = meterField.getText();
                String address = addressField.getText();
                String period = periodField.getText();

                MonthlyBill newBill = new MonthlyBill(
                        -1,
                        userId,
                        tariff,
                        energyUsed,
                        amountReceived,
                        energyRate,
                        amountDue,
                        meterType,
                        period,
                        address);

                if (MonthlyBillsRepository.AddBill(newBill)) {
                    JOptionPane.showMessageDialog(contentPane, "Bill successfully created");
                    new CustomerSearchWindow();
                    dispose();
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(contentPane, "Bill creation failed");
            }
        }
    }
}

package Views;


import DataAccess.MonthlyBillsRepository;
import DataAccess.UserRepository;
import Models.Customer;
import Models.MonthlyBill;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// View to add a bill for a customer
public class AddBillWindow extends JFrame implements ActionListener {
    private JButton backButton, addBillButton;
    private JTextField nameField, phoneField, addressField,
            tariffField, energyRateField, meterField, energyUsedField;
    private JComboBox periodComboBox;
    private Container contentPane;
    private Customer customer;

    // View to add a bill for a customer
    public AddBillWindow(Customer selectedCustomer) {
        // Initialize window and customer value
        customer = selectedCustomer;
        setTitle("KC Energy - Add Bill");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title for the pane
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(30,0,0,0));
        JLabel addBillLabel = new JLabel("Add Bill Form");
        addBillLabel.setFont(Constants.TITLE_FONT_LARGE);
        topPanel.add(addBillLabel);

        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.addActionListener(this);
        backButton.setFocusPainted(false);

        // Create the create button
        addBillButton = new JButton("Add Bill");
        addBillButton.addActionListener(this);

        // Create bottom panel and add buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        bottomPanel.add(backButton);
        bottomPanel.add(addBillButton);

        // Set the layout elements of the content pane
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buildBillPanel(), BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Builds and returns the panel that displays the necessary fields to fill out for a bill
    public JPanel buildBillPanel() {
        // Create name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        nameField.setText(customer.CustomerName);
        nameField.setEditable(false);

        // Create phone field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        phoneField.setText(customer.PhoneNumber);
        phoneField.setEditable(false);

        // Create address field
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        addressField.setText(customer.CurrentAddress);
        addressField.setEditable(false);

        // Create tariff field
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        tariffField.setText(Double.toString(customer.CurrentTariff));
        tariffField.setEditable(false);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        energyRateField.setText(Double.toString(customer.CurrentEnergyRate));
        energyRateField.setEditable(false);

        // Create meter type field
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        meterField.setText(customer.MeterType);
        meterField.setEditable(false);

        // Create energy used field
        JLabel energyUsedLabel = new JLabel("Energy Used (hours):");
        energyUsedField = new JTextField(Constants.TEXT_FIELD_WIDTH);

        // Create the billing period field
        JLabel periodLabel = new JLabel("Billing Period:");
        periodComboBox = new JComboBox(Constants.PERIOD_OPTIONS);

        // Create the panel and set the grid bag layout
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
        userDisplayPanel.add(periodComboBox, c);

        return userDisplayPanel;
    }

    // Route each button selected to the correct functionality
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new BillsDashboardWindow(customer);
            dispose();
        } else if (e.getSource() == addBillButton) {
            // Add a bill for a customer
            try {
                // Get all the necessary values for the monthly bill object
                int userId = customer.CustomerId;
                double tariff = Double.parseDouble(tariffField.getText());
                double energyUsed = Double.parseDouble(energyUsedField.getText());
                double amountReceived = 0.0;
                double energyRate = Double.parseDouble(energyRateField.getText());
                double amountDue = energyUsed * energyRate * (1 + tariff);
                String meterType = meterField.getText();
                String address = addressField.getText();
                String period = Objects.requireNonNull(periodComboBox.getSelectedItem()).toString();

                // Create a new monthly bill object
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

                // Call the database and add this bill for the customer
                if (MonthlyBillsRepository.AddBill(newBill)) {
                    JOptionPane.showMessageDialog(contentPane, "Bill successfully created");

                    // Route to the customer search window on completion
                    new BillsDashboardWindow(customer);
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

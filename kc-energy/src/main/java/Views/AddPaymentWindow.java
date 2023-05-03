package Views;

import DataAccess.CustomerPaymentRepository;
import DataAccess.UserRepository;
import Models.Customer;
import Models.MonthlyBill;
import Models.Payment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// View to add a payment for a customer
public class AddPaymentWindow extends JFrame implements ActionListener {
    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField,
            tariffField, energyRateField, meterField,
            amountDueField, amountReceivedField, energyUsedField,
            periodField, currentTariffField, currentMeterField,
            currentEnergyRateField, paymentAmountField;
    private Container contentPane;
    private Customer customer;
    private MonthlyBill bill;

    // View to add a payment for a customer
    public AddPaymentWindow(Customer selectedCustomer, MonthlyBill selectedBill) {
        // Initialize the selected customer and bill
        customer = selectedCustomer;
        bill = selectedBill;

        // Initialize the window
        setTitle("KC Energy - Add Payment");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title for the pane
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(30,0,0,0));
        JLabel addPaymentLabel = new JLabel("Add Payment Form");
        addPaymentLabel.setFont(Constants.TITLE_FONT_LARGE);
        topPanel.add(addPaymentLabel);

        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.addActionListener(this);

        // Create the create button
        createButton = new JButton("Add Payment");
        createButton.addActionListener(this);

        // Create bottom panel and add buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        bottomPanel.add(backButton);
        bottomPanel.add(createButton);

        // Set the content pane components
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buildPaymentInformationDisplayPanel(), BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Builds the panel that displays the payment information fields
    public JPanel buildPaymentInformationDisplayPanel() {
        // Create Customer Information Label
        JLabel customerInformationLabel = new JLabel("Customer Information:");
        customerInformationLabel.setFont(Constants.TITLE_FONT_MEDIUM);

        // Create Bills Table Label
        JLabel billsTableLabel = new JLabel("Bill Information:");
        billsTableLabel.setFont(Constants.TITLE_FONT_MEDIUM);

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
        JLabel addressLabel = new JLabel("Current Address:");
        addressField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        addressField.setText(customer.CurrentAddress);
        addressField.setEditable(false);

        // Create tariff field
        JLabel currentTariffLabel = new JLabel("Current Tariff:");
        currentTariffField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        currentTariffField.setText(Double.toString(customer.CurrentTariff));
        currentTariffField.setEditable(false);

        // Create energy rate field
        JLabel currentEnergyRateLabel = new JLabel("Current Energy Rate:");
        currentEnergyRateField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        currentEnergyRateField.setText(Double.toString(customer.CurrentEnergyRate));
        currentEnergyRateField.setEditable(false);

        // Create meter type field
        JLabel currentMeterLabel = new JLabel("Current Meter Type:");
        currentMeterField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        currentMeterField.setText(customer.MeterType);
        currentMeterField.setEditable(false);

        // Create meter type field
        JLabel billMeterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        meterField.setText(bill.MeterType);
        meterField.setEditable(false);

        // Create the billing period field
        JLabel periodLabel = new JLabel("Billing Period:");
        periodField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        periodField.setText(bill.Period);
        periodField.setEditable(false);

        // Create tariff field
        JLabel billTariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        tariffField.setText(Double.toString(bill.EnergyTariff));
        tariffField.setEditable(false);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        energyRateField.setText(Double.toString(bill.EnergyRate));
        energyRateField.setEditable(false);

        // Create energy used field
        JLabel energyUsedLabel = new JLabel("Energy Used:");
        energyUsedField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        energyUsedField.setText(Double.toString(bill.EnergyUsed));
        energyUsedField.setEditable(false);

        // Create the amount due field
        JLabel amountDueLabel = new JLabel("Amount Due:");
        amountDueField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        amountDueField.setText(Double.toString(bill.AmountDue));
        amountDueField.setEditable(false);

        // Create amount received field
        JLabel amountReceivedLabel = new JLabel("Amount Received:");
        amountReceivedField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        amountReceivedField.setText(Double.toString(bill.AmountReceived));
        amountReceivedField.setEditable(false);

        // Create the amount they want to add field
        JLabel amountToPayLabel = new JLabel("Payment Amount:");
        paymentAmountField = new JTextField(Constants.TEXT_FIELD_WIDTH);

        // Create the panel with the grid bag layout
        JPanel informationDisplayPanel = new JPanel();
        informationDisplayPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // row 0
        c.insets = new Insets(10, 2, 10, 2);
        c.gridy = 0;
        c.gridx = 0;
        informationDisplayPanel.add(customerInformationLabel, c);

        // Row 1
        c.insets = new Insets(2, 2, 2, 2);
        c.gridy = 1;
        c.gridx = 0;
        informationDisplayPanel.add(nameLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(nameField, c);
        c.gridx = 2;
        informationDisplayPanel.add(phoneLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(phoneField, c);

        // row 2
        c.gridy = 2;
        c.gridx = 0;
        informationDisplayPanel.add(addressLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(addressField, c);
        c.gridx = 2;
        informationDisplayPanel.add(currentTariffLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(currentTariffField, c);

        // row 3
        c.gridy = 3;
        c.gridx = 0;
        informationDisplayPanel.add(currentEnergyRateLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(currentEnergyRateField, c);
        c.gridx = 2;
        informationDisplayPanel.add(currentMeterLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(currentMeterField, c);

        // row 4
        c.insets = new Insets(30, 2, 10, 2);
        c.gridy = 4;
        c.gridx = 0;
        informationDisplayPanel.add(billsTableLabel, c);

        // row 5
        c.insets = new Insets(2, 2, 2, 2);
        c.gridy = 5;
        c.gridx = 0;
        informationDisplayPanel.add(billMeterLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(meterField, c);
        c.gridx = 2;
        informationDisplayPanel.add(periodLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(periodField, c);

        // row 6
        c.gridy = 6;
        c.gridx = 0;
        informationDisplayPanel.add(billTariffLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(tariffField, c);
        c.gridx = 2;
        informationDisplayPanel.add(energyRateLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(energyRateField, c);

        // row 7
        c.gridy = 7;
        c.gridx = 0;
        informationDisplayPanel.add(energyUsedLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(energyUsedField, c);
        c.gridx = 2;
        informationDisplayPanel.add(amountDueLabel, c);
        c.gridx = 3;
        informationDisplayPanel.add(amountDueField, c);

        // row 8
        c.gridy = 8;
        c.gridx = 0;
        informationDisplayPanel.add(amountReceivedLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(amountReceivedField, c);

        // row 9
        c.insets = new Insets(40, 2, 2, 2);
        c.gridy = 9;
        c.gridx = 0;
        informationDisplayPanel.add(amountToPayLabel, c);
        c.gridx = 1;
        informationDisplayPanel.add(paymentAmountField, c);

        return informationDisplayPanel;
    }

    // Route the button clicked to the correct action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new BillsDashboardWindow(customer);
            dispose();
        } else if (e.getSource() == createButton) {
            // Create the payment object to be added to a customer's bill in the database
            try {
                // Retrieve the necessary information for the payment
                int userId = customer.CustomerId;
                int billId = bill.BillId;
                double paymentAmount = Double.parseDouble(paymentAmountField.getText());

                // Create the new payment object
                Payment payment = new Payment(
                        userId,
                        billId,
                        -1,
                        paymentAmount);

                // Try calling the database and adding this payment for a customer's bill
                if (CustomerPaymentRepository.CreatePayment(payment)) {
                    JOptionPane.showMessageDialog(contentPane, "Payment success");
                    new BillsDashboardWindow(customer);
                    dispose();
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(contentPane, "Payment failed");
            }
        }
    }
}

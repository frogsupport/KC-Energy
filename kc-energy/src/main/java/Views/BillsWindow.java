package Views;

import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillsWindow extends JFrame implements ActionListener {

    private JButton backButton, editButton;
    private Container contentPane;
    private Customer customer;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;
    private final int FIELD_WIDTH = 10;

    public BillsWindow(Customer selectedCustomer) {
        customer = selectedCustomer;

        setTitle("KC Energy - View Customer");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(buildUserDisplayPanel(), BorderLayout.NORTH);

        contentPane.add(buildbottomPabel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel buildbottomPabel() {
        // Create the Add Bill button
        JButton addBillButton = new JButton("Add Bill");
        addBillButton.addActionListener(this);

        // Create the View bill buttons
        JButton viewBillButton = new JButton("View Bill");
        addBillButton.addActionListener(this);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(2, 2, 2, 2);

        // Add all components to panel //
        // row 0
        c.gridy = 0;
        c.gridx = 0;
        bottomPanel.add(addBillButton, c);
        c.gridx = 1;
        bottomPanel.add(viewBillButton, c);

        return bottomPanel;
    }

    public JPanel buildUserDisplayPanel() {
        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.addActionListener(this);

        // Create Edit User Button
        editButton = new JButton("Edit Customer");
        editButton.addActionListener(this);

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

        JPanel userDisplayPanel = new JPanel();
        userDisplayPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(2, 2, 2, 2);

        // Add all components to panel //

        // row 0
        c.gridy = 0;
        c.gridx = 0;
        userDisplayPanel.add(backButton, c);
        c.gridx = 1;
        userDisplayPanel.add(editButton, c);

        // row 1
        c.gridy = 1;
        c.gridx = 0;
        userDisplayPanel.add(nameLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(nameField, c);
        c.gridx = 2;
        userDisplayPanel.add(phoneLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(phoneField, c);

        // row 2
        c.gridy = 2;
        c.gridx = 0;
        userDisplayPanel.add(addressLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(addressField, c);
        c.gridx = 2;
        userDisplayPanel.add(meterLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(meterField, c);

        // row 3
        c.gridy = 3;
        c.gridx = 0;
        userDisplayPanel.add(energyRateLabel, c);
        c.gridx = 1;
        userDisplayPanel.add(energyRateField, c);
        c.gridx = 2;
        userDisplayPanel.add(tariffLabel, c);
        c.gridx = 3;
        userDisplayPanel.add(tariffField, c);

        return userDisplayPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Code to return to previous page
        if (e.getSource() == backButton) {
            new DashboardWindow();
            dispose();
        } else if ((e.getSource() == editButton)) {
            new EditUserWindow(customer);
            dispose();
        }
    }
}

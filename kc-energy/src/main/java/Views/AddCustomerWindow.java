package Views;

import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// View for creating a new customer
public class AddCustomerWindow extends JFrame implements ActionListener {
    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField;
    private JComboBox meterComboBox, tariffComboBox, energyRateComboBox;
    private Container contentPane;

    // View for creating a new customer
    public AddCustomerWindow() {
        // Initialize the window
        setTitle("KC Energy - Create Customer");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title for the pane
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(30,0,0,0));
        JLabel createCustomerLabel = new JLabel("Create Customer Form");
        createCustomerLabel.setFont(Constants.TITLE_FONT_LARGE);
        topPanel.add(createCustomerLabel);

        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        // Create the create button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        createButton = new JButton("Create Customer");
        createButton.addActionListener(this);
        bottomPanel.add(backButton);
        bottomPanel.add(createButton);

        // Set the content pane and its components
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buildCreateUserWindow(), BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Build and return the panel that has the necessary fields for customer creation
    public JPanel buildCreateUserWindow() {
        // Create name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(Constants.TEXT_FIELD_WIDTH);

        // Create phone field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(Constants.TEXT_FIELD_WIDTH);

        // Create address field
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(Constants.TEXT_FIELD_WIDTH);

        // Create tariff field
        JLabel tariffLabel = new JLabel("Tariff (percent - ex: 0.10):");
        tariffComboBox = new JComboBox(Constants.TARIFF_OPTIONS);
        tariffComboBox.setSelectedIndex(0);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate (dollars per hour):");
        energyRateComboBox = new JComboBox(Constants.ENERGY_RATE_OPTIONS);
        energyRateComboBox.setSelectedIndex(0);

        // Create meter type field
        JLabel meterLabel = new JLabel("Meter Type:");
        meterComboBox = new JComboBox(Constants.METER_OPTIONS);
        meterComboBox.setSelectedIndex(0);

        JPanel createUserPanel = new JPanel();
        createUserPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(20, 2, 2, 2);

        // row 0
        c.gridy = 0;
        c.gridx = 0;
        createUserPanel.add(nameLabel, c);
        c.gridx = 1;
        createUserPanel.add(nameField, c);

        // row 1
        c.gridy = 1;
        c.gridx = 0;
        createUserPanel.add(phoneLabel, c);
        c.gridx = 1;
        createUserPanel.add(phoneField, c);

        // row 2
        c.gridy = 2;
        c.gridx = 0;
        createUserPanel.add(addressLabel, c);
        c.gridx = 1;
        createUserPanel.add(addressField, c);

        // row 3
        c.gridy = 3;
        c.gridx = 0;
        createUserPanel.add(meterLabel, c);
        c.gridx = 1;
        createUserPanel.add(meterComboBox, c);

        // row 4
        c.gridy = 4;
        c.gridx = 0;
        createUserPanel.add(energyRateLabel, c);
        c.gridx = 1;
        createUserPanel.add(energyRateComboBox, c);

        // row 5
        c.gridy = 5;
        c.gridx = 0;
        createUserPanel.add(tariffLabel, c);
        c.gridx = 1;
        createUserPanel.add(tariffComboBox, c);

        return createUserPanel;
    }

    // Route the button clicked to the correct action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new CustomerSearchWindow(UserRepository.GetCustomers());
            dispose();
        } else if (e.getSource() == createButton) {
            try {
                // Get all the necessary field information for the customer to create
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                double tariff = Double.parseDouble(Objects.requireNonNull(tariffComboBox.getSelectedItem()).toString());
                double energyRate = Double.parseDouble(Objects.requireNonNull(energyRateComboBox.getSelectedItem()).toString());
                String meterType = Objects.requireNonNull(meterComboBox.getSelectedItem()).toString();

                // Create the new customer object
                Customer newCustomer = new Customer(
                        -1,
                        name,
                        phone,
                        address,
                        tariff,
                        energyRate,
                        meterType);

                // Try calling the database and appending the new customer
                 if (UserRepository.CreateCustomer(newCustomer)) {
                     JOptionPane.showMessageDialog(contentPane, "Customer successfully created");
                     new CustomerSearchWindow(UserRepository.GetCustomers());
                     dispose();
                 }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(contentPane, "Customer creation failed");
            }
        }
    }
}

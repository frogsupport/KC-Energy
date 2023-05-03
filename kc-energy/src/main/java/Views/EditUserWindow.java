package Views;
import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

// View for editing a customer
public class EditUserWindow extends JFrame implements ActionListener {
    private Customer customer;
    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField;
    private JComboBox meterComboBox, tariffComboBox, energyRateComboBox;
    private Container contentPane;

    // View for editing a customer
    public EditUserWindow(Customer selectedCustomer) {
        // Initialize the selected customer
        customer = selectedCustomer;

        // Initialize the window
        setTitle("KC Energy - Edit Customer");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title for the pane
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(30,0,0,0));
        JLabel editUserLabel = new JLabel("Edit User Form");
        editUserLabel.setFont(Constants.TITLE_FONT_LARGE);
        topPanel.add(editUserLabel);

        // Create return button
        backButton = new JButton("Return to Search Page");
        backButton.addActionListener(this);

        // Create the create button
        createButton = new JButton("Save");
        createButton.addActionListener(this);

        // Create bottom panel and add buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        bottomPanel.add(backButton);
        bottomPanel.add(createButton);

        // Add components to window
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buildEditUserWindow(), BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Build and return the panel with the customer information that the user can edit
    public JPanel buildEditUserWindow() {
        // Create name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        nameField.setText(customer.CustomerName);

        // Create phone field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        phoneField.setText(customer.PhoneNumber);

        // Create address field
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(Constants.TEXT_FIELD_WIDTH);
        addressField.setText(customer.CurrentAddress);

        // Create tariff field
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffComboBox = new JComboBox(Constants.TARIFF_OPTIONS);
        tariffComboBox.setSelectedIndex(0);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateComboBox = new JComboBox(Constants.ENERGY_RATE_OPTIONS);
        energyRateComboBox.setSelectedIndex(0);

        // Create meter type field
        JLabel meterLabel = new JLabel("Meter Type:");
        meterComboBox = new JComboBox(Constants.METER_OPTIONS);
        meterComboBox.setSelectedIndex(0);

        JPanel editUserPanel = new JPanel();
        editUserPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(20, 2, 2, 2);

        // row 0
        c.gridy = 0;
        c.gridx = 0;
        editUserPanel.add(nameLabel, c);
        c.gridx = 1;
        editUserPanel.add(nameField, c);

        // row 1
        c.gridy = 1;
        c.gridx = 0;
        editUserPanel.add(phoneLabel, c);
        c.gridx = 1;
        editUserPanel.add(phoneField, c);

        // row 2
        c.gridy = 2;
        c.gridx = 0;
        editUserPanel.add(addressLabel, c);
        c.gridx = 1;
        editUserPanel.add(addressField, c);

        // row 3
        c.gridy = 3;
        c.gridx = 0;
        editUserPanel.add(meterLabel, c);
        c.gridx = 1;
        editUserPanel.add(meterComboBox, c);

        // row 4
        c.gridy = 4;
        c.gridx = 0;
        editUserPanel.add(energyRateLabel, c);
        c.gridx = 1;
        editUserPanel.add(energyRateComboBox, c);

        // row 5
        c.gridy = 5;
        c.gridx = 0;
        editUserPanel.add(tariffLabel, c);
        c.gridx = 1;
        editUserPanel.add(tariffComboBox, c);

        return editUserPanel;
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
                // Get the data from the fields for the customer
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                double tariff = Double.parseDouble(Objects.requireNonNull(tariffComboBox.getSelectedItem()).toString());
                double energyRate = Double.parseDouble(Objects.requireNonNull(energyRateComboBox.getSelectedItem()).toString());
                String meterType = Objects.requireNonNull(meterComboBox.getSelectedItem()).toString();

                // Create the new customer object
                Customer updatedCustomer = new Customer(
                        customer.CustomerId,
                        name,
                        phone,
                        address,
                        tariff,
                        energyRate,
                        meterType);

                // Try calling the database and updating the customer
                if (UserRepository.UpdateCustomer(updatedCustomer)) {
                    JOptionPane.showMessageDialog(contentPane, "Customer successfully updated");
                    new CustomerSearchWindow(UserRepository.GetCustomers());
                    dispose();
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(contentPane, "Customer update failed");
            }
        }
    }
}

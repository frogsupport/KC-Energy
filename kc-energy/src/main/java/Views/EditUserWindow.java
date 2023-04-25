package Views;
import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserWindow extends JFrame implements ActionListener {

    private Customer customer;
    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;
    private Container contentPane;

    public EditUserWindow(Customer selectedCustomer) {
        customer = selectedCustomer;

        setTitle("KC Energy - Edit Customer");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create return button
        backButton = new JButton("Return to Dashbaord");
        backButton.addActionListener(this);

        // Create the create button
        createButton = new JButton("Save");
        createButton.addActionListener(this);

        // Add components to window
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(backButton, BorderLayout.NORTH);
        contentPane.add(buildEditUserWindow(), BorderLayout.CENTER);
        contentPane.add(createButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel buildEditUserWindow() {
        // Create name field
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setText(customer.CustomerName);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Create phone field
        JPanel phonePanel = new JPanel();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setText(customer.PhoneNumber);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        // Create address field
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setText(customer.CurrentAddress);
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);

        // Create tariff field
        JPanel tariffPanel = new JPanel();
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(20);
        tariffField.setText(Double.toString(customer.CurrentTariff));
        tariffPanel.add(tariffLabel);
        tariffPanel.add(tariffField);

        // Create energy rate field
        JPanel energyRatePanel = new JPanel();
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(20);
        energyRateField.setText(Double.toString(customer.CurrentEnergyRate));
        energyRatePanel.add(energyRateLabel);
        energyRatePanel.add(energyRateField);

        // Create meter type field
        JPanel meterPanel = new JPanel();
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(20);
        meterField.setText(customer.MeterType);
        meterPanel.add(meterLabel);
        meterPanel.add(meterField);

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
        editUserPanel.add(meterField, c);

        // row 4
        c.gridy = 4;
        c.gridx = 0;
        editUserPanel.add(energyRateLabel, c);
        c.gridx = 1;
        editUserPanel.add(energyRateField, c);

        // row 5
        c.gridy = 5;
        c.gridx = 0;
        editUserPanel.add(tariffLabel, c);
        c.gridx = 1;
        editUserPanel.add(tariffField, c);

        return editUserPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new CustomerSearchWindow(UserRepository.GetCustomers());
            dispose();
        } else if (e.getSource() == createButton) {
            try {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                double tariff = Double.parseDouble(tariffField.getText());
                double energyRate = Double.parseDouble(energyRateField.getText());
                String meterType = meterField.getText();

                Customer updatedCustomer = new Customer(
                        customer.CustomerId,
                        name,
                        phone,
                        address,
                        tariff,
                        energyRate,
                        meterType);

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

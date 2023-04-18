package Views;
import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUser extends JFrame implements ActionListener {

    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;
    Container contentPane;

    public CreateUser() {
        setTitle("KC Energy - Create Customer");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.addActionListener(this);

        // Create name field
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Create phone field
        JPanel phonePanel = new JPanel();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        // Create address field
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);

        // Create tariff field
        JPanel tariffPanel = new JPanel();
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(20);
        tariffPanel.add(tariffLabel);
        tariffPanel.add(tariffField);

        // Create energy rate field
        JPanel energyRatePanel = new JPanel();
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(20);
        energyRatePanel.add(energyRateLabel);
        energyRatePanel.add(energyRateField);

        // Create meter type field
        JPanel meterPanel = new JPanel();
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(20);
        meterPanel.add(meterLabel);
        meterPanel.add(meterField);

        // Create the create button
        createButton = new JButton("Create Customer");
        createButton.addActionListener(this);

        // Add components to window
        contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(8, 1));
        contentPane.add(backButton);
        contentPane.add(namePanel);
        contentPane.add(phonePanel);
        contentPane.add(addressPanel);
        contentPane.add(tariffPanel);
        contentPane.add(energyRatePanel);
        contentPane.add(meterPanel);
        contentPane.add(createButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Code to return to previous page
            new UserSearch();
            dispose();
        } else if (e.getSource() == createButton) {
            try {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                double tariff = Double.parseDouble(tariffField.getText());
                double energyRate = Double.parseDouble(energyRateField.getText());
                String meterType = meterField.getText();

                Customer newCustomer = new Customer(
                        -1,
                        name,
                        phone,
                        address,
                        tariff,
                        energyRate,
                        meterType);

                 if (UserRepository.CreateCustomer(newCustomer)) {
                     JOptionPane.showMessageDialog(contentPane, "Customer successfully created");
                     new UserSearch();
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

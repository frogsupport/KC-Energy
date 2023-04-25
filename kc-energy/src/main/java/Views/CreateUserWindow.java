package Views;

import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserWindow extends JFrame implements ActionListener {

    private JButton backButton, createButton;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;
    Container contentPane;

    public CreateUserWindow() {
        setTitle("KC Energy - Create Customer");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create return button
        backButton = new JButton("Return to Previous Page");
        backButton.addActionListener(this);

        // Create the create button
        createButton = new JButton("Create Customer");
        createButton.addActionListener(this);

        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(backButton, BorderLayout.NORTH);
        contentPane.add(buildcreateUserWindow(), BorderLayout.CENTER);
        contentPane.add(createButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel buildcreateUserWindow() {
        // Create name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        // Create phone field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);

        // Create address field
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);

        // Create tariff field
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(20);

        // Create energy rate field
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(20);

        // Create meter type field
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(20);

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
        createUserPanel.add(meterField, c);

        // row 4
        c.gridy = 4;
        c.gridx = 0;
        createUserPanel.add(energyRateLabel, c);
        c.gridx = 1;
        createUserPanel.add(energyRateField, c);

        // row 5
        c.gridy = 5;
        c.gridx = 0;
        createUserPanel.add(tariffLabel, c);
        c.gridx = 1;
        createUserPanel.add(tariffField, c);

        return createUserPanel;
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

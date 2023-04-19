package Views;

import DataAccess.UserRepository;
import Models.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static DataAccess.UserRepository.GetCustomers;

public class BillsWindow extends JFrame implements ActionListener {

    private JButton createButton, searchButton, viewButton, editButton, deleteButton;
    private JTextField searchField;
    private JList<String> customerJList;
    private ArrayList<Customer> customers;
    private String[] customersArray;
    private DefaultTableModel customerTableModel;
    private JTable customerTable;
    private Container contentPane;
    private Customer customer;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;

    public BillsWindow(Customer selectedCustomer) {
        customer = selectedCustomer;

        setTitle("KC Energy - View Customer");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create name field
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setText(customer.CustomerName);
        nameField.setEditable(false);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Create phone field
        JPanel phonePanel = new JPanel();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setText(customer.PhoneNumber);
        phoneField.setEditable(false);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        // Create address field
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setText(customer.CurrentAddress);
        addressField.setEditable(false);
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);

        // Create tariff field
        JPanel tariffPanel = new JPanel();
        JLabel tariffLabel = new JLabel("Tariff:");
        tariffField = new JTextField(20);
        tariffField.setText(Double.toString(customer.CurrentTariff));
        tariffField.setEditable(false);
        tariffPanel.add(tariffLabel);
        tariffPanel.add(tariffField);

        // Create energy rate field
        JPanel energyRatePanel = new JPanel();
        JLabel energyRateLabel = new JLabel("Energy Rate:");
        energyRateField = new JTextField(20);
        energyRateField.setText(Double.toString(customer.CurrentEnergyRate));
        energyRateField.setEditable(false);
        energyRatePanel.add(energyRateLabel);
        energyRatePanel.add(energyRateField);

        // Create meter type field
        JPanel meterPanel = new JPanel();
        JLabel meterLabel = new JLabel("Meter Type:");
        meterField = new JTextField(20);
        meterField.setText(customer.MeterType);
        meterField.setEditable(false);
        meterPanel.add(meterLabel);
        meterPanel.add(meterField);

        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets for all components
        c.insets = new Insets(2, 2, 2, 2);

        // column 0
        c.gridx = 0;

        // row 0
        c.gridy = 0;

        // constraints passed in
        contentPane.add(nameLabel, c);

        // row 1
        c.gridy = 1;

        // constraints passed in
        contentPane.add(addressPanel, c);

        // column 0
        c.gridx = 0;

        // row 2
        c.gridy = 2;

        // constraints passed in
        contentPane.add(phonePanel, c);

        // column 1
        c.gridx = 1;

        // constraints passed in
        contentPane.add(meterPanel, c);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected customer if selected
        Customer selectedCustomer = null;
        if (customerTable.getSelectedRow() != -1) {
            selectedCustomer = customers.get(customerTable.getSelectedRow());
        }

        // Switch buttons
        if (e.getSource() == createButton) {
            new CreateUserWindow();
            dispose();
        } else if (e.getSource() == viewButton) {
            // Code to view selected user
        } else if ((e.getSource() == editButton) && (selectedCustomer != null)) {
            new EditUserWindow(selectedCustomer);
            dispose();
        } else if ((e.getSource() == deleteButton) && (selectedCustomer != null)) {
            deleteCustomer(selectedCustomer.CustomerId);
        }
    }

    public void deleteCustomer(int customerId) {
        if (UserRepository.DeleteCustomer(customerId)) {
            JOptionPane.showMessageDialog(contentPane, "Customer successfully deleted");
            new DashboardWindow();
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(contentPane, "Customer deletion failed");
        }
    }
}

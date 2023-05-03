package Views;

import DataAccess.UserRepository;
import Models.Customer;
import Models.MonthlyBill;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static DataAccess.MonthlyBillsRepository.GetCustomerBills;

// A view that displays user information as well as all of their bills.
// Has the ability to add bills and payments, as well as viewing a bill
public class BillsDashboardWindow extends JFrame implements ActionListener {

    private JButton backButton, editButton,
            addBillButton, viewBillButton,deleteButton, addPaymentButton;
    private Container contentPane;
    private Customer customer;
    private JTextField nameField, phoneField, addressField, tariffField, energyRateField, meterField;
    private DefaultTableModel billsTableModel;
    private ArrayList<MonthlyBill> bills;
    private JList<String> billsJList;
    private JTable billsJTable;
    private String[] billsArray;

    // A view that displays user information as well as all of their bills.
    // Has the ability to add bills and payments, as well as viewing a bill
    public BillsDashboardWindow(Customer selectedCustomer) {
        // Set the selected customer
        customer = selectedCustomer;

        // Initialize the window
        setTitle("KC Energy - View Customer");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title for the pane
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(30,0,0,0));
        JLabel energyDashboardLabel = new JLabel("Customer Energy Dashboard");
        energyDashboardLabel.setFont(Constants.TITLE_FONT_LARGE);
        topPanel.add(energyDashboardLabel);

        // Get the content pane and set the layout and components
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buildCenterPanel(), BorderLayout.CENTER);
        contentPane.add(buildBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel buildCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(buildUserDisplayPanel(), BorderLayout.NORTH);
        centerPanel.add(buildBillDisplayPanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    // Builds a panel that contains the customer's bills
    public JScrollPane buildBillDisplayPanel() {
        // Create bills list
        bills = GetCustomerBills(customer.CustomerId);
        String[] column_names =
                {
                        "Billing Period",
                        "Amount Received",
                        "Amount Due",
                        "Payment Status"
                };

        billsTableModel = new DefaultTableModel(column_names, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        billsArray = new String[bills.size()];
        for (int i = 0; i < bills.size(); i++) {
            billsArray[i] = bills.get(i).toString();
            billsTableModel.addRow(bills.get(i).getRow());
        }

        billsJList = new JList<>(billsArray);
        billsJTable = new JTable(billsTableModel);
        billsJTable.setSelectionMode(0);
        JScrollPane scrollPane = new JScrollPane(billsJTable);

        return scrollPane;
    }

    // Builds a panel that contains the customer's information
    public JPanel buildUserDisplayPanel() {
        // Create Customer Information Label
        JLabel customerInformationLabel = new JLabel("Customer Information:");
        customerInformationLabel.setFont(Constants.TITLE_FONT_MEDIUM);

        // Create Bills Table Label
        JLabel billsTableLabel = new JLabel("Customer Bills:");
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

        JPanel userDisplayPanel = new JPanel();
        userDisplayPanel.setLayout(new GridBagLayout());

        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();

        // insets
        c.insets = new Insets(15, 2, 2, 2);

        // row 0
//        c.gridy = 0;
//        c.gridx = 0;
//        userDisplayPanel.add(backButton, c);
//        c.gridx = 1;
//        userDisplayPanel.add(editButton, c);
//        c.gridx = 2;
//        userDisplayPanel.add(deleteButton, c);

        // Row 0
        c.insets = new Insets(20, 2, 10, 2);
        c.gridy = 0;
        c.gridx = 0;
        userDisplayPanel.add(customerInformationLabel, c);

        // row 1
        c.insets = new Insets(2, 2, 2, 2);
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

        // row 4
        c.insets = new Insets(20, 2, 20, 2);
        c.gridy = 4;
        c.gridx = 0;
        userDisplayPanel.add(billsTableLabel, c);

        return userDisplayPanel;
    }

    // Returns the bottom panel and its buttons
    public JPanel buildBottomPanel() {
        // Create return button
        backButton = new JButton("Return to Search Page");
        backButton.addActionListener(this);
        backButton.setFocusPainted(false);

        // Create Edit User Button
        editButton = new JButton("Edit Customer");
        editButton.addActionListener(this);

        // Create user delete button
        deleteButton = new JButton("Delete Customer");
        deleteButton.addActionListener(this);

        // Create the Add Bill button
        addBillButton = new JButton("Add Bill");
        addBillButton.addActionListener(this);

        // Add payment button
        addPaymentButton = new JButton("Add Payment");
        addPaymentButton.addActionListener(this);

        // Create the View bill buttons
        viewBillButton = new JButton("View Bill");
        viewBillButton.addActionListener(this);

        // Set and build the panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        bottomPanel.add(addBillButton);
        bottomPanel.add(addPaymentButton);
        bottomPanel.add(viewBillButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);

        return bottomPanel;
    }

    // Route the button clicked to the correct action
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected customer if selected
        MonthlyBill selectedBill = null;
        if (billsJTable.getSelectedRow() != -1) {
            selectedBill = bills.get(billsJTable.getSelectedRow());
        }

        if (e.getSource() == backButton) {
            new CustomerSearchWindow(UserRepository.GetCustomers());
            dispose();
        } else if ((e.getSource() == editButton)) {
            new EditUserWindow(customer);
            dispose();
        } else if (e.getSource() == addBillButton) {
            new AddBillWindow(customer);
            dispose();
        } else if (e.getSource() == deleteButton) {
            deleteCustomer(customer.CustomerId);
        } else if ((e.getSource() == addPaymentButton) && (selectedBill != null)) {
            new AddPaymentWindow(customer, selectedBill);
            dispose();
        } else if ((e.getSource() == viewBillButton) && (selectedBill != null)) {
            JOptionPane.showMessageDialog(contentPane, selectedBill.printCustomerBill(customer));
        }
    }

    // Customer deletion
    public void deleteCustomer(int customerId) {
        if (UserRepository.DeleteCustomer(customerId)) {
            JOptionPane.showMessageDialog(contentPane, "Customer successfully deleted");
            new CustomerSearchWindow(UserRepository.GetCustomers());
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(contentPane, "Customer deletion failed");
        }
    }
}

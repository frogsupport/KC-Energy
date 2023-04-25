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
import static DataAccess.UserRepository.GetCustomersByName;

public class CustomerSearchWindow extends JFrame implements ActionListener {

    private JButton createButton, searchButton, viewButton, editButton, deleteButton;
    private JTextField searchField;
    private JList<String> customerJList;
    private ArrayList<Customer> customers;
    private String[] customersArray;
    private DefaultTableModel customerTableModel;
    private JTable customerTable;
    Container contentPane;

    public CustomerSearchWindow(ArrayList<Customer> customers) {
        this.customers = customers;
        setTitle("KC Energy - Search Customers");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create search bar and button
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create user list
        String[] column_names =
                {
                        "Name",
                        "Phone Number",
                        "Address"
                };

        customerTableModel = new DefaultTableModel(column_names, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        customersArray = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            customersArray[i] = customers.get(i).toString();
            customerTableModel.addRow(customers.get(i).getRow());
        }

        customerJList = new JList<>(customersArray);
        customerTable = new JTable(customerTableModel);
        customerTable.setSelectionMode(0);
        JScrollPane scrollPane = new JScrollPane(customerTable);

        // Create bottom buttons
        JPanel bottomPanel = new JPanel();
        createButton = new JButton("Create Customer");
        createButton.addActionListener(this);
        viewButton = new JButton("View Customer");
        viewButton.addActionListener(this);
        editButton = new JButton("Edit Customer");
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete Customer");
        deleteButton.addActionListener(this);
        bottomPanel.add(createButton);
        bottomPanel.add(viewButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);

        // Add components to window
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(searchPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

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
        } else if ((e.getSource() == viewButton) && (selectedCustomer != null)) {
            // Code to view selected user
            new BillsDashboardWindow(selectedCustomer);
            dispose();
        } else if ((e.getSource() == editButton) && (selectedCustomer != null)) {
            new EditUserWindow(selectedCustomer);
            dispose();
        } else if ((e.getSource() == deleteButton) && (selectedCustomer != null)) {
            deleteCustomer(selectedCustomer.CustomerId);
        } else if ((e.getSource() == searchButton) && (searchField.getText().length() > 0)) {
            new CustomerSearchWindow(GetCustomersByName(searchField.getText()));
            dispose();
        } else if ((e.getSource() == searchButton) && (searchField.getText().length() == 0)) {
            new CustomerSearchWindow(GetCustomers());
            dispose();
        }
    }

    public void deleteCustomer(int customerId) {
        if (UserRepository.DeleteCustomer(customerId)) {
            JOptionPane.showMessageDialog(contentPane, "Customer successfully deleted");
            new CustomerSearchWindow(GetCustomers());
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(contentPane, "Customer deletion failed");
        }
    }

    public static void main(String[] args) {
        new CustomerSearchWindow(GetCustomers());
    }
}

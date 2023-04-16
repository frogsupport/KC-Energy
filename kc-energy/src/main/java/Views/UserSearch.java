package Views;
import Models.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static DataAccess.UserRepository.GetCustomers;

public class UserSearch extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JList<String> userList;
    private DefaultTableModel customerTableModel;
    private JTable customerTable;

    public UserSearch(ArrayList<Customer> customers) {
        setTitle("User Search");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create search bar and button
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        String[] column_names =
                {
                        "Customer Id",
                        "Name",
                        "Phone Number",
                        "Address",
                        "Current Tariff",
                        "Energy Rate",
                        "Meter Type"
                };

        customerTableModel = new DefaultTableModel(column_names, 0);
        for (Customer customer : customers) {
            customerTableModel.addRow(customer.getRow());
        }

        customerTable = new JTable(customerTableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);

        // Add components to window
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(searchPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = GetCustomers();

        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).CustomerName);
        }

        // Create and show the window
        new UserSearch(customers);
    }
}

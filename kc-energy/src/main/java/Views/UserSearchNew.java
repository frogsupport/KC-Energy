package Views;

import Models.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static DataAccess.UserRepository.GetCustomers;

public class UserSearchNew extends JFrame implements ActionListener {

    private JButton createButton, searchButton, viewButton, editButton, deleteButton;
    private JTextField searchField;
    private JList<String> customerJList;
    private ArrayList<Customer> customers;
    private String[] customersArray;
    private DefaultTableModel customerTableModel;
    private JTable customerTable;

    public UserSearchNew() {
        setTitle("KC Energy");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create create button


        // Create search bar and button
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create user list
        customers = GetCustomers();
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
        createButton = new JButton("Create User");
        createButton.addActionListener(this);
        viewButton = new JButton("View User");
        viewButton.addActionListener(this);
        editButton = new JButton("Edit User");
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(this);
        bottomPanel.add(createButton);
        bottomPanel.add(viewButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);

        // Add components to window
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(searchPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            new CreateUser();
            dispose();
            // Code to create new user
        } else if (e.getSource() == viewButton) {
            // Code to view selected user
        } else if (e.getSource() == editButton) {
            // Code to edit selected user
        } else if (e.getSource() == deleteButton) {
            // Code to delete selected user
        }
    }

    public static void main(String[] args) {
        new UserSearchNew();
    }
}

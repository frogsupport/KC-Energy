package DataAccess;

import Models.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static DataAccess.DbConnection.dbCon;

public class UserRepository {
    public static ArrayList<Customer> GetCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = "SELECT * FROM kc_energy.customer;";

            // send and execute SQL query in Database software
            ResultSet result = statement.executeQuery(query);

            // Display the contents of the result set.
            while (result.next()) {
                int customerId = result.getInt("customer_id");
                String customerName = result.getString("customer_name");
                String phoneNumber = result.getString("phone_number");
                String currentAddress = result.getString("current_address");
                double currentTariff = result.getDouble("current_tariff");
                double currentEnergyRate = result.getDouble("current_energy_rate");
                String meterType = result.getString("meter_type");
                Date deletedDate = result.getDate("deleted_date");

                Customer customer = new Customer(
                        customerId,
                        customerName,
                        phoneNumber,
                        currentAddress,
                        currentTariff,
                        currentEnergyRate,
                        meterType);

                customers.add(customer);
            }

            // close connection
            connection.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return customers;
    }

    public static boolean CreateCustomer(Customer customer) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "INSERT INTO kc_energy.customer (customer_name, phone_number, current_address, "
                    + "current_tariff, current_energy_rate, meter_type) "
                    + "VALUES ('%s', '%s', '%s', %s, %s, '%s');",
                    customer.CustomerName,
                    customer.PhoneNumber,
                    customer.CurrentAddress,
                    customer.CurrentTariff,
                    customer.CurrentEnergyRate,
                    customer.MeterType);

            // send and execute SQL query in Database software
            int queryResult = statement.executeUpdate(query);

            if (queryResult == 1) {
                System.out.println("inserted successfully");
                return true;
            }
            else {
                System.out.println("insert failed");
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean UpdateCustomer(Customer customer) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "UPDATE kc_energy.customer "
                    + "SET customer_name = '%s', phone_number = '%s', "
                    + "current_address = '%s', current_tariff = %s, "
                    + "current_energy_rate = %s, meter_type = '%s' "
                    + "WHERE customer_id = %s;",
                    customer.CustomerName,
                    customer.PhoneNumber,
                    customer.CurrentAddress,
                    customer.CurrentTariff,
                    customer.CurrentEnergyRate,
                    customer.MeterType,
                    customer.CustomerId);

            // send and execute SQL query in Database software
            int result = statement.executeUpdate(query);

            if (result == 1) {
                System.out.println("updated successfully");
                return true;
            }
            else {
                System.out.println("update failed");
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean DeleteCustomer(int customerId) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "DELETE FROM kc_energy.customer "
                            + "WHERE customer_id = %s;",
                    customerId);

            // send and execute SQL query in Database software
            int result = statement.executeUpdate(query);

            if (result == 1) {
                System.out.println("deleted successfully");
                return true;
            }
            else {
                System.out.println("delete failed");
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}

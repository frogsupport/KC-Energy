package DataAccess;

import Models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static DataAccess.DbConnection.dbCon;

public class TestDataAccess {
    public static void main(String[] args) {

    }

    public static void testCreateCustomer() {
        try {
            Customer customer = new Customer(
                    -1,
                    "Bill",
                    "111-222-3344",
                    "test lane",
                    0.25,
                    0.50,
                    "Electric 2000");

            UserRepository.CreateCustomer(customer);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void testGetCustomers() {
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
                System.out.printf("%s %s %s %s %s %s %s %s\n",
                        result.getInt("customer_id"),
                        result.getString("customer_name"),
                        result.getString("phone_number"),
                        result.getString("current_address"),
                        result.getDouble("current_tariff"),
                        result.getDouble("current_energy_rate"),
                        result.getString("meter_type"),
                        result.getDate("deleted_date"));
            }

            // close connection
            connection.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

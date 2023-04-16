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

                System.out.printf("%s %s %s %s %s %s %s %s\n",
                        customerId,
                        customerName,
                        phoneNumber,
                        currentAddress,
                        currentTariff,
                        currentEnergyRate,
                        meterType,
                        deletedDate);
            }

            // close connection
            connection.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return customers;
    }
}

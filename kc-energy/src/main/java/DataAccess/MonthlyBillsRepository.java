package DataAccess;

import Models.Customer;
import Models.MonthlyBill;
import Models.Payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static DataAccess.DbConnection.dbCon;

public class MonthlyBillsRepository {
    public static ArrayList<MonthlyBill> GetCustomerBills(int customerId) {
        ArrayList<MonthlyBill> bills = new ArrayList<>();

        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format("SELECT * FROM kc_energy.monthly_bill WHERE user_id = %s;", customerId);

            // send and execute SQL query in Database software
            ResultSet result = statement.executeQuery(query);

            // Display the contents of the result set.
            while (result.next()) {
                int billId = result.getInt("bill_id");
                int userId = result.getInt("user_id");
                double tariff = result.getDouble("energy_tariff");
                double energyUsed = result.getDouble("energy_used");
                double amountReceived = result.getDouble("amount_received");
                double energyRate = result.getDouble("energy_rate");
                double amountDue = result.getDouble("amount_due");
                String meterType = result.getString("meter_type");
                String period = result.getString("period");
                String address = result.getString("address");

                MonthlyBill bill = new MonthlyBill(
                        billId,
                        userId,
                        tariff,
                        energyUsed,
                        amountReceived,
                        energyRate,
                        amountDue,
                        meterType,
                        period,
                        address);

                bills.add(bill);
            }

            // close connection
            connection.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return bills;
    }

    public static boolean AddBill(MonthlyBill bill) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "INSERT INTO `kc_energy`.`monthly_bill` "
                    + "(`user_id`, `energy_tariff`, `energy_used`, "
                    + "`amount_received`, `energy_rate`, `amount_due`, "
                    + "`meter_type`, `period`, `address`) "
                    + "VALUES (%s, %s, %s, %s, %s, %s, '%s', '%s', '%s');",
                    bill.UserId,
                    bill.EnergyTariff,
                    bill.EnergyUsed,
                    bill.AmountReceived,
                    bill.EnergyRate,
                    bill.AmountDue,
                    bill.MeterType,
                    bill.Period,
                    bill.Address);

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

    public static boolean AddBillPayment(Payment payment) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "UPDATE kc_energy.monthly_bill SET amount_received = amount_received + %s "
                            + "WHERE bill_id = %s;",
                    payment.PaymentAmount,
                    payment.BillId);

            // send and execute SQL query in Database software
            int queryResult = statement.executeUpdate(query);

            if (queryResult == 1) {
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
}

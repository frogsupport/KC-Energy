package DataAccess;

import Models.Customer;
import Models.Payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import static DataAccess.DbConnection.dbCon;

public class CustomerPaymentRepository {

    public static String GetPayments(int billId) {
        String payments = new String();

        try {

            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format("SELECT * FROM kc_energy.customer_payment WHERE bill_id = %s;", billId);

            // send and execute SQL query in Database software
            ResultSet result = statement.executeQuery(query);

            // Display the contents of the result set.
            while (result.next()) {
                int paymentId = result.getInt("payment_id");
                int userId = result.getInt("user_id");
                double paymentAmount = result.getDouble("payment_amount");

                Payment payment = new Payment(
                        userId,
                        billId,
                        paymentId,
                        paymentAmount);

                payments = payments + payment.toString();
            }

            // close connection
            connection.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return payments;
    }

    public static boolean CreatePayment(Payment payment) {
        try {
            // Create a connection to the database.
            Connection connection = dbCon();

            // create JDBC statement object
            Statement statement = connection.createStatement();

            // prepare SQL query
            String query = String.format(
                    "INSERT INTO kc_energy.customer_payment (bill_id, user_id, payment_amount) "
                            + "VALUES (%s, %s, %s);",
                    payment.BillId,
                    payment.UserId,
                    payment.PaymentAmount);

            // send and execute SQL query in Database software
            int queryResult = statement.executeUpdate(query);

            if (queryResult == 1) {
                System.out.println("inserted successfully");

            }
            else {
                System.out.println("insert failed");
                return false;
            }

            // Update the bill object with the payment amount
            if (MonthlyBillsRepository.AddBillPayment(payment)) {
                System.out.println("Bill successfully updated");
                return true;
            }
            else {
                System.out.println("Bill update failed");
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}

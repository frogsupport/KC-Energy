package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    // db variables
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/kc_energy";
    private static final String dbUser = "root";
    private static final String dbPassword = "password";

    public static Connection dbCon() throws Exception {

        // register MySQL thin driver with DriverManager service
        // It is optional for JDBC4.x version
        //Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}

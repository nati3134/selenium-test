package utilities;


import java.sql.DriverManager;

public class ManageDB extends CommonOperations {

    public static void initConnectionMYSQL(String dbURL, String Username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, Username, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error occurred while connecting to DB, see details: " + e);
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error occurred while closing DB connection, see details: " + e);
        }
    }
}

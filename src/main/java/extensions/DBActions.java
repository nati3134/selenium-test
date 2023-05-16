package extensions;

import io.qameta.allure.Step;
import utilities.ManageDB;

import java.sql.ResultSet;

public class DBActions extends ManageDB {

    @Step("Display query results")
    public static ResultSet getQueryResults(String query) {
        try {
            rs = stmt.executeQuery(query);
            rs.next();
        } catch (Exception e) {
            System.out.println("Error occurred while trying to perform a query, see details: " + e);
        }
        return rs;
    }
}

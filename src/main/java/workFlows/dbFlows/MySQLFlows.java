package workFlows.dbFlows;

import extensions.DBActions;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLFlows extends DBActions {

    @Step("Verify first records")
    public static List<String> verifyFirstRecordValues() {
        List<String> firstRecord = new ArrayList<>();
        return firstRecord;
    }

}




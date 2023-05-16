package tomaGradeSanity;

import extensions.UIActions;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.CommonOperations;
import utilities.ManagePages;
import workFlows.webFlows.TomaGrade.LoginFlows;

import static utilities.UsefulMethods.getData;
//@Listeners(utilities.listeners.class)

public class LoginTests extends CommonOperations {

    @BeforeClass
    public void startWebSession() {
        initBrowser(getData("browserType"), getData("tomaGradeURL"), Integer.parseInt(getData("timeout")),
                Integer.parseInt(getData("pageLoadTimeout")));
        ManagePages.initWeb();
    }

    @Test(description = "test_01_verifyCredentialsAreRequired", enabled = false)
    @Description("Test Description: Verify TomaGrade requires valid login credentials")
    public void test_01_verifyCredentialsAreRequired() {
        LoginFlows.verifyWrongOrMissingCredentialsAlerts(getData("loginEmailAddress"), "Wrong password");
    }

    @Test(description = "test_02_loginToTomaGrade")
    @Description("Test Description: Verify successful login to TomaGrade")
    public void test_02_verifySuccessfulLogin() {
        LoginFlows.tomaGradeSignIn();
    }

    @AfterClass
    public void closeSession() {
        try {
            UIActions.click(HomePage.signOutButton);
            wait.until(ExpectedConditions.urlToBe("https://tomaxtst.tomasafe.com/tomaxlogin"));
        } catch (Exception e) {
            System.out.println("Login failed - closing session " + e);
        }
        driver.quit();
    }
}

package tomaGradeSanity;

import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.CommonOperations;
import utilities.ManagePages;
import workFlows.webFlows.TomaGrade.GradeArrayFlows;
import workFlows.webFlows.TomaGrade.LoginFlows;

import static utilities.UsefulMethods.getData;
//@Listeners(utilities.listeners.class)

public class NonSafeExamsTests extends CommonOperations {

    final String noGradeCourseName = "JAVA";

    @BeforeClass
    public void startWebSession() {
        initBrowser(getData("browserType"), getData("tomaGradeURL"), Integer.parseInt(getData("timeout")),
                Integer.parseInt(getData("pageLoadTimeout")));
        ManagePages.initWeb();
        LoginFlows.tomaGradeSignIn(
        );
    }

    //TODO Add verification for the grade array button being selected with green text
    @Test(description = "test_01_verifyNoGradeArrayIsDefined")
    @Description("Test Description: Verify the selected course doesn't have a defined grade array to begin with")
    public void test_01_verifyNoGradeArrayIsDefined() {
        GradeArrayFlows.verifyEmptyGradeArray(noGradeCourseName);
    }

    @Test(description = "test_02_verifySomeEmptyGradeArrayProperties")
    @Description("Test Description: Verify some properties of an empty grade array")
    public void test_03_verifyEmptyGradeArrayProperties() {
        GradeArrayFlows.verifyAdditionalEmptyGradeArrayProperties();
    }

    @AfterClass
    public void closeSession() {
        //if (runSingleFile)
        driver.quit();
    }
}

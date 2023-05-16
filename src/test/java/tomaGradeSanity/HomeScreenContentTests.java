package tomaGradeSanity;


import extensions.Verifications;
import io.qameta.allure.Description;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.CommonOperations;
import utilities.ManagePages;
import workFlows.webFlows.TomaGrade.HomeScreenFlows;
import workFlows.webFlows.TomaGrade.LoginFlows;

import static utilities.UsefulMethods.getData;

//@Listeners(utilities.listeners.class)

public class HomeScreenContentTests extends CommonOperations {


    final String[] expectedCourseDetails = {"סטטיסטיקה" + "\n" + "150221", "מס' קורס" + "\n" + "101" + "\n" + "|" + "\n" + "Ori Naaman",
            "2023 | סמסטר א | מועד א | 27/03/2023", "3 מבחנים נשארו לבדיקה מתוך 3"};
    final String[] expectedActionButtonsHiddenText = {"ממשק ניהול", "notifications", "בחר שפה", "עזרה", "התנתק"};
    final String[] expectedTopTabsText = {"פרטי הבחינה", "מערך ציונים", "נספחים", "הערות סגל", "בנק הערות", "דוחות"};
    final String selectedCourseTopLeftExpectedDetails = "סטטיסטיקה - 2023 | סמסטר א | מועד א | 150221";
    final String[] otherOperationsOptionsList = {"שיתוף", "ייצוא קובץ ציונים", "ייצוא סטטיסטיקת ציונים", "ייצוא סטטיסטיקת הערות",
            "אפס קורס", "אפס בדיקה לבחינה שנבחרה", "אפס סימניה", "מחיקת קבצים שסומנו", "הפוך עריכה לזמינה", "אשר ציון לבחינות שנבחרו",
            "שמור חיפוש", "צור מנה", "העברה לבדיקה שניה", "בחר כציון לפרסום", "שליחת מייל לסטודנטים"};
    final int expectedNumOfOtherOperations = 15;
    final String selectedCourseName = "סטטיסטיקה";
    final String displayedAndSelectedStudentExams = "מוצגים 3 מתוך 3 | נבחרו 0 מתוך 3";
    final String[] expectedProcessSteps = {"שלב 1 - הגדרות ראשוניות", "שלב 2 - בדיקה", "שלב 3 - סיום בדיקה"};

    @BeforeClass
    public void startWebSession() {
        try {
            initBrowser(getData("browserType"), getData("tomaGradeURL"), Integer.parseInt(getData("timeout")),
                    Integer.parseInt(getData("pageLoadTimeout")));
            ManagePages.initWeb();
            LoginFlows.tomaGradeSignIn();
        } catch (Exception e) {
            System.out.println("Either session failed to start correctly or Login failed - closing web session " + e);
            driver.quit();
        }
    }

    @Test(description = "test_01_verifyLastConnectionDetailsAreDisplayed", priority = 1)
    @Description("Test Description: Verify last connection date and time details are displayed")
    public void test_01_verifyLastConnectionDetailsAreDisplayed() {
        HomeScreenFlows.verifyLastConnectionDetails();
    }

    @Test(description = "test_02_verifyTomaxImageIsDisplayed", priority = 2)
    @Description("Test Description: Verify lecturer's pending courses names are displayed")
    public void test_02_verifyTomaxImageIsDisplayed() {
        Verifications.visibilityOfElement(HomePage.tomaxIcon);
    }

    @Test(description = "test_03_verifyActionsButtons", priority = 3)
    @Description("Test Description: Verify actions buttons are displayed and enabled")
    public void test_03_verifyActionsButtonsAreAvailable() {
        HomeScreenFlows.verifyActionButtonsAvailability();
    }

    @Test(description = "test_04_verifyActionsButtonsHiddenText", priority = 4)
    @Description("Test Description: Verify actions button's hidden text")
    public void test_04_verifyActionsButtonsHiddenText() {
        HomeScreenFlows.verifyActionsButtonsHiddenText(expectedActionButtonsHiddenText);
    }

    @Test(description = "test_05_verifyAvailableTabs", priority = 5)
    @Description("Test Description: Verify displayed tabs text")
    public void test_05_verifyTopTabsAvailabilityAndContent() {
        HomeScreenFlows.verifyTopTabsContent(expectedTopTabsText);
    }

    @Test(description = "test_06_verifySelectedCourseDetails", priority = 6)
    @Description("Test Description: Verify details of the selected course")
    public void test_06_verifySelectedCourseDetails() throws InterruptedException {
        HomeScreenFlows.verifySelectedCourseDetails(selectedCourseName, expectedCourseDetails);
    }

    @Test(description = "test_07_verifyTopLeftCourseDetails", priority = 7, dependsOnMethods = {"test_06_verifySelectedCourseDetails"})
    @Description("Test Description: Verify top left section details (Selected course)")
    public void test_07_verifySelectedCourseTopLeftDetails() {
        HomeScreenFlows.verifySelectedTopLeftCourseDetails(selectedCourseTopLeftExpectedDetails);
    }

    @Test(description = "test_08_verifyNumOfDisplayedAndSelectedStudentsExams", priority = 8, dependsOnMethods =
            {"test_06_verifySelectedCourseDetails"})
    @Description("Test Description: Verify the number of displayed and selected students exams ")
    public void test_08_verifyNumOfSelectedExams() {
        HomeScreenFlows.verifyNumOfDisplayedAndSelectedStudentsExams(displayedAndSelectedStudentExams);
    }

    @Test(description = "test_09_verifyProcessStepsAreDisplayed", priority = 9)
    @Description("Test Description: Verify process steps are displayed and their text")
    public void test_09_verifyProcessStepsAreDisplayed() {
        HomeScreenFlows.verifyProcessStepsAreDisplayedAndTheirText(expectedProcessSteps);
    }

    //todo  - verify different background colors and hidden guidance
    @Test(description = "test_10_verifyProcessStepsStatus", priority = 10)
    @Description("Test Description: Verify process steps status")
    public void test_10_verifyEachProcessStepStatus() {
        HomeScreenFlows.verifyProcessStepsStatus();
    }

    @Test(description = "test_11_verifyAvailabilityAndNumberOfOtherOperations", priority = 11)
    @Description("Test Description: Verify availability and number of other operations")
    public void test_11_verifyOtherOperationMenuOptions() {
        HomeScreenFlows.verifyOtherOperationsMenu(expectedNumOfOtherOperations, otherOperationsOptionsList);
    }

    @AfterClass
    public void closeSession() {
        if (runSingleFile)
            driver.quit();
    }
}


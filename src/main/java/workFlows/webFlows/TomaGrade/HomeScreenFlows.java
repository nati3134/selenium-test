package workFlows.webFlows.TomaGrade;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonOperations;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFlows extends CommonOperations {


    //Todo:Verify if the displayed last connection date holds a valid date time format
    @Step("Verify last connection details")
    public static void verifyLastConnectionDetails() {
        String dateFormat = "HH:mm , dd/MM/yyyy";
        // String lastConnection = UsefulMethods.getPCDateTime(dateFormat);
        System.out.println(HomePage.lastLoginDate.getText());
        Verifications.visibilityOfElement(HomePage.lastLoginDate);
    }

    @Step("Verify pending course details")
    public static void verifySelectedCourseDetails(String courseToSelect, String[] expectedDetails) throws InterruptedException {
        List<WebElement> displayedCourseDetails = new ArrayList<>();
//        try {
//            if (HomePage.selectedUncheckedCourse.isDisplayed())
//                UIActions.click(HomePage.cleanSelectedCourseFilterButton);
//        } catch (Exception e) {
//            System.out.println("No course is currently selected - nothing to clean" + e);
//        }
        UIActions.moveToElement(HomePage.uncheckedCoursesSearchField);
        UIActions.updateText(HomePage.uncheckedCoursesSearchField, courseToSelect);
        UIActions.clickEnter(HomePage.uncheckedCoursesSearchField);
        UIActions.moveToElement(HomePage.pendingCoursesNamesList.get(0));
        UIActions.click(HomePage.pendingCoursesNamesList.get(0));
        Thread.sleep(3000);
        displayedCourseDetails.add(0, HomePage.courseDetailsUpperContainerList.get(0));
        displayedCourseDetails.add(1, HomePage.courseDetailsUpperContainerList.get(1));
        displayedCourseDetails.add(2, HomePage.courseDetailsUpperContainerList.get(2));
        displayedCourseDetails.add(3, HomePage.courseDetailsUpperContainerList.get(3));
        UIActions.moveToElement(HomePage.selectedCourseName);
        Verifications.textInElement(HomePage.selectedCourseName, courseToSelect);
        Verifications.textInEachListIndex(displayedCourseDetails, expectedDetails);
    }

    @Step("Verify num of displayed and selected student exams")
    public static void verifyNumOfDisplayedAndSelectedStudentsExams(String expectedStatus) {
        Verifications.textInElement(HomePage.leftSideGradingProgressIndicator, expectedStatus);
    }

    @Step("Select a specific course")
    public static void selectCourseToGrade(String courseToSelect) throws InterruptedException {
//        try {
//            if (HomePage.selectedUncheckedCourse.isDisplayed())
//                UIActions.click(HomePage.cleanSelectedCourseFilterButton);
//        } catch (Exception e) {
//            System.out.println("No course is currently selected - nothing to clean" + e);
//        }
        UIActions.moveToElement(HomePage.uncheckedCoursesSearchField);
        UIActions.updateText(HomePage.uncheckedCoursesSearchField, courseToSelect);
        UIActions.clickEnter(HomePage.uncheckedCoursesSearchField);
        UIActions.moveToElement(HomePage.pendingCoursesNamesList.get(0));
        UIActions.click(HomePage.pendingCoursesNamesList.get(0));
        Thread.sleep(2000);
        Verifications.textInElement(HomePage.pendingCoursesNamesList.get(0), courseToSelect);
    }

    @Step("Start a new grading process")
    public static void startGrading() {
        UIActions.click(HomePage.startGradingProcessButton);
        wait.until(ExpectedConditions.elementToBeClickable(GradingPage.gradingByStudentButton));
        try {
            if (GradingPage.examSetupQuestionAlert.isDisplayed())
                wait.until(ExpectedConditions.elementToBeClickable(GradingPage.closeExamSetupQuestionAlertButton));
            UIActions.click(GradingPage.closeExamSetupQuestionAlertButton);
        } catch (Exception e) {
            System.out.println("Exam setup question alert dialog did NOT popup" + e);
        }
    }

    @Step("Verify action buttons availability")
    public static void verifyActionButtonsAvailability() {
        Verifications.visibilityOfAllElementsOfAList(HomePage.actionButtonsList);
        Verifications.availabilityOfAllElementsInAList(HomePage.actionButtonsList);
    }

    @Step("Verify pending course details")
    public static void verifyTopTabsContent(String[] expectedTopTabsText) {
        Verifications.visibilityOfAllElementsOfAList(HomePage.tomaGradeTopTabsList);
        Verifications.availabilityOfAllElementsInAList(HomePage.tomaGradeTopTabsList);
        Verifications.textInEachListIndex(HomePage.tomaGradeTopTabsList, expectedTopTabsText);
    }

    @Step("Verify process steps are displayed and their text")
    public static void verifyProcessStepsAreDisplayedAndTheirText(String[] expectedProcessSteps) {
        Verifications.visibilityOfAllElementsOfAList(HomePage.processStepsList);
        Verifications.textInEachListIndex(HomePage.processStepsList, expectedProcessSteps);
    }

    @Step("Verify process steps status")
    public static void verifyProcessStepsStatus() {
        Verifications.verifyElementAttributeContainsAGivenValue(HomePage.processStepsGuidanceList.get(0), "class",
                "Finished");
        Verifications.verifyElementAttributeContainsAGivenValue(HomePage.processStepsGuidanceList.get(1), "class",
                "Active");
        Verifications.verifyElementAttributeDoesNotContainsAGivenValue(HomePage.processStepsGuidanceList.get(2), "class",
                "Active");
        Verifications.verifyElementAttributeDoesNotContainsAGivenValue(HomePage.processStepsGuidanceList.get(2), "class",
                "Finished");
    }

    @Step("Verify pending course details")
    public static void verifyActionsButtonsHiddenText(String[] expectedHiddenTextList) {
        List<WebElement> actionButtonHiddenTextList = new ArrayList<>();
        for (WebElement elem : HomePage.actionButtonsTextFirstFour) {
            actionButtonHiddenTextList.add(elem);
            System.out.println(elem.getAttribute("title"));
        }
        actionButtonHiddenTextList.add(HomePage.signOutButton);
        System.out.println(HomePage.signOutButton.getAttribute("title"));
        Verifications.attributeValueInEachListIndex(actionButtonHiddenTextList, "title", expectedHiddenTextList);
    }

    @Step("Verify selected course details")
    public static void verifySelectedTopLeftCourseDetails(String expectedCourseDetails) {
        System.out.println(HomePage.selectedCourseTopLeftDetails.getText());
        Verifications.textInElement(HomePage.selectedCourseTopLeftDetails, expectedCourseDetails);
    }

    @Step("Verify other operations menu content")
    public static void verifyOtherOperationsMenu(int numOfOtherOperations, String[] otherOperationOptions) {
        UIActions.click(HomePage.otherOperationsButton);
        Verifications.numberOfElementsInAList(HomePage.otherOperationsList, numOfOtherOperations);
        Verifications.visibilityOfAllElementsOfAList(HomePage.otherOperationsList);
        Verifications.textInEachListIndex(HomePage.otherOperationsList, otherOperationOptions);
        for (WebElement elem : HomePage.otherOperationsList) {
            System.out.println(elem.getText());
        }
    }
}
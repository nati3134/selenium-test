package tomaGradeSanity;

import extensions.UIActions;
import io.qameta.allure.Description;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.CommonOperations;
import utilities.ManagePages;
import workFlows.webFlows.TomaGrade.GradingProcessFlows;
import workFlows.webFlows.TomaGrade.HomeScreenFlows;
import workFlows.webFlows.TomaGrade.LoginFlows;


import static utilities.UsefulMethods.getData;

//@Listeners(utilities.listeners.class)

public class OnlineExamsTests extends CommonOperations {

//    final String gradedOnlineCourseName = "אסטרונומיה";
//    final String gradedOnlineCourseDetails = "שם קורס: אסטרונומיה | מס' קורס: 103 | הועבר לבדיקה לידי: | מרצה: Ori Naaman";
    final String gradedOnlineCourseName = "הנדסת מכונות";
    final String gradedOnlineCourseDetails = "שם קורס: הנדסת מכונות | מס' קורס: 101 | הועבר לבדיקה לידי: TomaGrade Automation | מרצה: Ori Naaman";
    final String selectedModeButtonColor = "rgba(233, 233, 233, 1)";
    final String previousExamText = "למבחן הקודם";
    final String nextExamText = "למבחן הבא";
    final int numOfStudents = 5;
    final String[] expectedActionLinksText = {"שאלות הבחינה", "הערות", "סימניות", "שקופיות", "סטטיסטיקה"};
    final int numOfAnnotationToolbarSections = 9;
    final String greatFeedbackComment = "יפה!";
    final String missingDetailsComment = "חסר פירוט...";
    final String halfPointsComment = "חשיבה בכיוון אבל תוצאה לא נכונה";
    final String completelyIncorrectComment = "לא בכיוון!";
    final String noAnswerComment = "לא ענית אז 0 נקודות!";

    @BeforeClass
    public void startWebSession() {
        try {
            initBrowser(getData("browserType"), getData("tomaGradeURL"), Integer.parseInt
                            (getData("timeout")),
                    Integer.parseInt(getData("pageLoadTimeout")));
            ManagePages.initWeb();
            LoginFlows.tomaGradeSignIn();
        } catch (Exception e) {
            System.out.println("Either session failed to start correctly or login failed - closing web session " + e);
            driver.quit();
        }
        try {
            HomeScreenFlows.selectCourseToGrade(gradedOnlineCourseName);
            HomeScreenFlows.startGrading();
        } catch (Exception e) {
            System.out.println("Either selecting a course or start grading failed - closing web session " + e);
            driver.quit();
        }
    }

    @Test(description = "test_01_verifyModes")
    @Description("Test Description: Verify available grading modes and their default")
    public void test_01_verifyAvailableGradingModesAndTheirDefault() throws InterruptedException {
        GradingProcessFlows.verifyGradingModesDefaultAndVisibility(selectedModeButtonColor);
    }

    @Test(description = "test_02_verifySelectedCourseDetails")
    @Description("Test Description: Complete an end to end grading process for an online exam using annotations")
    public void test_02_verifySelectedCourseDetails() {
        GradingProcessFlows.verifyGradedCourseDetails(gradedOnlineCourseName, gradedOnlineCourseDetails);
    }

    @Test(description = "test_03_verifyNextAndPreviousExamButtons")
    @Description("Test Description: Verify next and previous exam buttons are displayed and clickable")
    public void test_03_verifyNextAndPreviousExamButtons() {
        GradingProcessFlows.verifyNextAndPreviousExamButtons(previousExamText, nextExamText);
    }

    @Test(description = "test_04_verifyStudentIDDisplayedInLowerToolbar")
    @Description("Test Description: Verify student ID is displayed in the lower toolbar")
    public void test_04_verifyStudentID() {
        GradingProcessFlows.verifyLowerToolbarStudentIDIsDisplayed();
    }

    @Test(description = "test_05_verifyCurrentPageNumberIsDisplayed")
    @Description("Test Description: Verify the current page number is displayed in the lower toolbar")
    public void test_05_verifyPageNumberIsDisplayed() {
        GradingProcessFlows.verifyLowerToolbarPageNumberIsDisplayed();
    }

    @Test(description = "test_06_verifyApproveScoreButtonProperties")
    @Description("Test Description: Verify approve button is displayed, clickable, its color and background color")
    public void test_06_verifyApproveButtonAvailability() {
        GradingProcessFlows.verifyApproveScoreButtonProperties();
    }

    @Test(description = "test_07_verifyCorrectNumberOfExamines")
    @Description("Test Description: Verify correct number of examines")
    public void test_07_verifyCorrectNumberOfExamines() {
        GradingProcessFlows.verifyNumOfExamines(numOfStudents);
    }

    @Test(description = "test_08_verifyBackToStudentListButtonProperties")
    @Description("Test Description: Verify back to student list button is available and clickable")
    public void test_08_verifyBackToStudentsButtonProperties() {
        GradingProcessFlows.verifyBackToStudentListButtonProperties();
    }

    @Test(description = "test_09_verifyActionLinksButtonsAreDisplayedAndEnabled")
    @Description("Test Description: Verify action links button are displayed and clickable")
    public void test_09_verifyActionLinksButtonsAreDisplayedAndEnabled() {
        GradingProcessFlows.verifyActionLinksAreAvailable();
    }

    //todo - make it work - mouse hover works but the text is not catch
    @Test(description = "test_10_verifyActionLinksIsDisplayedWhenHovered", enabled = false)
    @Description("Test Description: Verify action links hidden text is displayed when hovered")
    public void test_10_verifyActionLinksHiddenTextDisplayedWhenHovered() throws InterruptedException {
        GradingProcessFlows.verifyActionLinksHiddenTextIsDisplayedWhenHovered();
    }

    @Test(description = "test_11_verifyActionLinksHiddenText")
    @Description("Test Description: Verify action links hidden text is correct")
    public void test_11_verifyActionLinksHiddenText() {
        GradingProcessFlows.verifyActionLinksHiddenText(expectedActionLinksText);
    }

    @Test(description = "test_12_verifyAnnotationBarInternalDivision")
    @Description("Test Description: Verify action links hidden text is correct")
    public void test_12_verifyNumOfAnnotationToolbarSections() {
        GradingProcessFlows.verifyAnnotationToolbarInternalDivision(numOfAnnotationToolbarSections);
    }

    //Student 1 question 1 - main G annotation and calc buttons - Max possible grade
    @Test(description = "test_13_verify_MaximumPointsWereGiven_MainGAnnotation")
    @Description("Test Description: Verify maximum points and comment were given for selected question using main G " +
            "annotation and calc buttons")
    public void test_13_verifyMaxPointsAndCommentCorrectnessUsingMainGAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(1);
        GradingProcessFlows.verifyGAnnotatorContainerIsSelected();
        GradingProcessFlows.gradeMaxPossiblePointsUsingMainGAnnotationAndCalcButtons(1, "1",
                greatFeedbackComment, 0);
        GradingProcessFlows.verifyCorrectGradeForASpecificQuestion(0, "25");
    }

    //Student 1 sub question 4.1 - Main G annotation
    @Test(description = "test_14_verifyCorrectGradeWasGiven_MainGAnnotation(sub question)")
    @Description("Test Description: Verify correct grade and comment were given for selected sub question using main G annotation")
    public void test_14_verifyCorrectGradeUsingMainGAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(1);
        GradingProcessFlows.verifyGAnnotatorContainerIsSelected();
        GradingProcessFlows.gradeUsingGMainAnnotation(7, "4.1", 5, greatFeedbackComment,
                1);
        GradingProcessFlows.verifyCorrectGradeForASpecificSubQuestion(0, "5");
    }

    //Student 1 question 5 - V annotation
    @Test(description = "test_15_verifyMaxPossibleGradeWasGiven_VAnnotation")
    @Description("Test Description: Verify max possible grade was given for selected question using V annotation")
    public void test_15_verifyMaxPossibleGradeUsingVAnnotation_question() throws InterruptedException {
        GradingProcessFlows.selectStudent(1);
        GradingProcessFlows.gradeFullPointsUsingVAnnotation(6, 13);
        GradingProcessFlows.verifyCorrectGradeForAQuestionUsingVAnnotation(3, 8);
    }

    //Student 1 second sub question 4.2.1 - V annotation
    @Test(description = "test_16_verifyMaxPossibleGradeWasGiven_VAnnotation(second sub question)")
    @Description("Test Description: Verify max possible grade was given for selected second sub question using V annotation")
    public void test_16_verifyMaxPossibleGradeUsingVAnnotation_secondSubQuestion() throws InterruptedException {
        GradingProcessFlows.selectStudent(1);
        GradingProcessFlows.gradeFullPointsUsingVAnnotation(4, 9);
        GradingProcessFlows.verifyCorrectGradeForASecondSubQuestionUsingVAnnotation(0, 6);
    }

    //Student 1 - second sub question 4.2.2 - inner G annotation
    @Test(description = "test_17_verifyCorrectGradeWasGiven_GInternalGAnnotation")
    @Description("Test Description: Verify correct grade and comment were given for selected question using internal G annotation")
    public void test_17_verifyCorrectGradeAndCommentCorrectnessUsingInternalGAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(1);
        GradingProcessFlows.gradeUsingInnerGAnnotation(11, "4.2.2", 10, greatFeedbackComment,
                0);
        GradingProcessFlows.verifyCorrectGradeForASpecificSecondSubQuestion(1, "10");
    }

    //Student 2 sub question 4.1 - V annotation
    @Test(description = "test_18_verifyMaxPossibleGradeWasGiven_VAnnotation(second sub question)")
    @Description("Test Description: Verify max possible grade was given for selected second sub question using V annotation")
    public void test_18_verifyMaxPossibleGradeUsingVAnnotation_SubQuestion() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.gradeFullPointsUsingVAnnotation(3, 7);
        GradingProcessFlows.verifyCorrectGradeForASubQuestionUsingVAnnotation(0, 4);
    }

    //Student 2 question 1 - crossed V annotation
    @Test(description = "test_19_verifyHalfMaxGradeWasGiven_CrossedVAnnotation")
    @Description("Test Description: Verify half of max possible grade was given for selected question using crossed V annotation")
    public void test_19_verifyHalfOfMaxPossibleGradeUsingCrossedVAnnotation_question() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.gradeHalfPointsUsingCrossedVAnnotation(0, 1);
        GradingProcessFlows.verifyCorrectGradeForAQuestionUsingCrossedVAnnotation(0, 0);
    }

    //Student 2 question 5 - right G annotation icon
    @Test(description = "test_20_Grade using right G icon")
    @Description("Test Description: Verify correct grade and comment were given for selected question using right G icon")
    public void test_20_verifyCorrectGradeUsingRightGAnnotationIcon() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.gradeUsingRightGAnnotationIcon(13, 6, 8, "חסר פירוט",
                0);
        GradingProcessFlows.verifyCorrectGradeForASpecificQuestion(3, "8");
    }

    //Student 2 second sub question 4.2.1 - X annotation
    @Test(description = "test_21_verifyZeroGradeWasGiven_XAnnotation")
    @Description("Test Description: Verify zero grade was given for selected second sub question using X annotation")
    public void test_21_verifyZeroGradeAndCommentCorrectnessUsingXAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.gradeZeroPointsUsingXAnnotation(4, 9);
        GradingProcessFlows.verifyCorrectGradeForASpecificSecondSubQuestion(0, "0");
    }

    //Student 2 second sub question 4.2.2 - No Answer annotation
    @Test(description = "test_22_verifyNoAnswerIndicationWereGiven_NoAnswerAnnotation")
    @Description("Test Description: Verify no answer indication was given for selected second sub question using no answer annotation")
    public void test_22_verifyNoAnswerIndication() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.IndicateNoAnswer(5, 11, "secondSubQuestion");
    }

    //Student 2 second sub question 4.2.1 - Adding a text comment //TODO - Make it possible to execute without cleaning previous text comments
    @Test(description = "test_23_AddATextComment")
    @Description("Test Description: Add a text comment and verify it was added to the page and to saved comment list")
    public void test_23_AddATextComment() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.addATextComment(9, completelyIncorrectComment);
    }

    //Student 2 second sub question 4.2.2 - Adding a sticky note comment //TODO - catch the stick note container
    @Test(description = "test_24_AddAStickyNoteComment")
    @Description("Test Description: Add a sticky note comment and verify it was added to the page and to saved comment list")
    public void test_24_AddAStickNoteComment() throws InterruptedException {
        GradingProcessFlows.selectStudent(2);
        GradingProcessFlows.addAStickNoteComment(11, noAnswerComment);
    }

    //Student 3 - Question 5 plus annotation
    @Test(description = "test_25_verifyCorrectGradeWasGiven_PlusAnnotation",enabled = false)
    @Description("Test Description: Verify max possible points and correct comment were given for selected question using " +
            "Plus annotation")
    public void test_25_verifyMaxPossiblePointsAndCommentCorrectnessUsingPlusAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(3);
        GradingProcessFlows.gradeMaxPointsUsingPlusAnnotationAndTypeGrade(13, "5", greatFeedbackComment,
                0);
        GradingProcessFlows.verifyCorrectGradeForASpecificQuestion(3, "10");
    }

    //Student 3 - Question 1 minus annotation
    @Test(description = "test_26_verifyCorrectGradeWasGiven_MinusAnnotation",enabled = false)
    @Description("Test Description: Verify correct grade and comment were given for selected question using Minus annotation")
    public void test_26_verifyCorrectGradeAndCommentCorrectnessUsingMinusAnnotation() throws InterruptedException {
        GradingProcessFlows.selectStudent(3);
        GradingProcessFlows.gradesUsingMinusAnnotationAndTypeGrade(1, "1", 5,
                "חסר פירוט", 0 );
        GradingProcessFlows.verifyCorrectGradeForASpecificQuestion(0, "20");
    }

    //Student 3 sub question 4.1 - crossed V annotation
    @Test(description = "test_27_verifyHalfMaxGradeWasGiven_CrossedVAnnotation")
    @Description("Test Description: Verify half of max possible grade was given for selected sub question using crossed V annotation")
    public void test_27_verifyHalfOfMaxPossibleGradeUsingCrossedVAnnotation_subQuestion() throws InterruptedException {
        GradingProcessFlows.selectStudent(3);
        GradingProcessFlows.gradeHalfPointsUsingCrossedVAnnotation(3, 7);
        GradingProcessFlows.verifyCorrectGradeForASubQuestionUsingCrossedVAnnotation(0, 4);
    }

    //Student 3 second sub question 4.2.1 - crossed V annotation
    @Test(description = "test_28_verifyHalfMaxGradeWasGiven_CrossedVAnnotation")
    @Description("Test Description: Verify half of max possible grade was given for selected second sub question using crossed V annotation")
    public void test_28_verifyHalfOfMaxPossibleGradeUsingCrossedVAnnotation_secondSubQuestion() throws InterruptedException {
        GradingProcessFlows.selectStudent(3);
        GradingProcessFlows.gradeHalfPointsUsingCrossedVAnnotation(4, 9);
        GradingProcessFlows.verifyCorrectGradeForASecondSubQuestionUsingCrossedVAnnotation(0, 6);
    }

    @AfterClass
    public void CloseSession() throws InterruptedException {
       GradingProcessFlows.deleteAllSavedTextComments();
       GradingProcessFlows.deleteAllManuallyGivenGradesAndCommentsFromAllStudents();
        //if (runSingleFile)
        driver.quit();
    }
}

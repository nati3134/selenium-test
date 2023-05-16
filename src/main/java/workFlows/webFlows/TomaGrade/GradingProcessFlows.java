package workFlows.webFlows.TomaGrade;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.tomaGrade.GradingPage;
import utilities.CommonOperations;
import utilities.UsefulMethods;

import javax.crypto.spec.PSource;
import javax.swing.text.html.CSS;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GradingProcessFlows extends CommonOperations {

    //--- General Content verification flows ---
    @Step("Grading modes default and visibility")
    public static void verifyGradingModesDefaultAndVisibility(String selectedModeButtonColor) {
        Verifications.availabilityOfElement(GradingPage.gradingByStudentButton);
        Verifications.getAndVerifyColor(GradingPage.gradingByStudentButton, selectedModeButtonColor);
        Verifications.visibilityOfElement(GradingPage.gradingByQuestionButton);
    }

    @Step("Verify pending course details")
    public static void verifyGradedCourseDetails(String gradedCourseName, String expectedCourseDetails) {
        System.out.println(GradingPage.gradedCourseDetails.getText());
        Verifications.textInElement(GradingPage.gradedCourseDetails, expectedCourseDetails);
    }

    @Step("Verify next and previous exam buttons availability")
    public static void verifyNextAndPreviousExamButtons(String previousButtonText, String nextButtonText) {
        Verifications.visibilityOfElement(GradingPage.nextExamArrowButton);
        Verifications.availabilityOfElement(GradingPage.nextExamArrowButton);
        Verifications.visibilityOfElement(GradingPage.previousExamArrowButton);
        Verifications.availabilityOfElement(GradingPage.previousExamArrowButton);
        Verifications.textInElement(GradingPage.previousExamArrowButton, previousButtonText);
        Verifications.textInElement(GradingPage.nextExamArrowButton, nextButtonText);
    }

    @Step("Verify lower toolbar elements text")
    public static void verifyLowerToolbarStudentIDIsDisplayed() {
        System.out.println(GradingPage.examineDetails.getText());
        Verifications.visibilityOfElement(GradingPage.examineDetails);
    }

    @Step("Verify page number is displayed in the lower toolbar")
    public static void verifyLowerToolbarPageNumberIsDisplayed() {
        System.out.println(GradingPage.currentPageNumber.getText());
        Verifications.visibilityOfElement(GradingPage.currentPageNumber);
    }

    @Step("Verify approve button properties")
    public static void verifyApproveScoreButtonProperties() {
        Verifications.visibilityOfElement(GradingPage.approveScoreButton);
        Verifications.availabilityOfElement(GradingPage.approveScoreButton);
        Verifications.getAndVerifyColor(GradingPage.approveScoreButton, "rgba(255, 255, 255, 1)");
        Verifications.getAndVerifyBackgroundColor(GradingPage.approveScoreButton, "rgba(110, 159, 27, 1)");
    }

    @Step("Verify correct number of examines")
    public static void verifyNumOfExamines(int expectedNumOfStudents) {
        Verifications.verifyWebElementsListSize(GradingPage.questionsRowList, expectedNumOfStudents);
    }

    @Step("Verify back to student list button properties")
    public static void verifyBackToStudentListButtonProperties() {
        Verifications.visibilityOfElement(GradingPage.backToStudentListButton);
        Verifications.availabilityOfElement(GradingPage.backToStudentListButton);
    }

    @Step("Verify grading process action links")
    public static void verifyActionLinksAreAvailable() {
        for (WebElement elem : GradingPage.gradingProcessActionLinks) {
            Verifications.visibilityOfElement(elem);
            Verifications.availabilityOfElement(elem);
        }
    }

    @Step("Verify grading process action links hidden text")
    public static void verifyActionLinksHiddenText(String[] actionLinksExpectedText) {
        for (int i = 0; i < GradingPage.gradingProcessActionLinksText.size(); i++) {
            String indexText = GradingPage.gradingProcessActionLinksText.get(i).getAttribute("data-value");
            System.out.println(indexText);
            Verifications.compareStringValues(indexText, actionLinksExpectedText[i]);
        }
    }

    @Step("Verify grading process action links hidden text")
    public static void verifyActionLinksHiddenTextIsDisplayedWhenHovered() throws InterruptedException {
        for (WebElement elem : GradingPage.gradingProcessActionLinks) {
            UIActions.mouseHoverOnElement(elem);
            Thread.sleep(1000);
            Verifications.visibilityOfAllElementsOfAList(GradingPage.gradingProcessActionLinksText);
        }
    }

    @Step("Verify annotation bar is divided into 9 sections")
    public static void verifyAnnotationToolbarInternalDivision(int expectedNumOfAnnotationToolbarSections) {
        Verifications.verifyWebElementsListSize(AnnotationPage.annotationToolbarSections, expectedNumOfAnnotationToolbarSections);
    }

    //---Separate verification flows---
    @Step("Verify correct garde is displayed for a specific question")
    public static void verifyCorrectGradeForASpecificQuestion(int QuestionGradeFieldIndex, String expectedGrade) {
        Verifications.compareStringValues(GradingPage.questionGradeFieldList.get(QuestionGradeFieldIndex)
                .getAttribute("value"), expectedGrade);
    }

    @Step("Verify correct garde is displayed for a specific sub question")
    public static void verifyCorrectGradeForASpecificSubQuestion(int subQuestionGradeFieldIndex, String expectedGrade) {
        Verifications.compareStringValues(GradingPage.subQuestionGradeFieldList.get(subQuestionGradeFieldIndex)
                .getAttribute("value"), expectedGrade);
    }

    @Step("Verify correct garde is displayed for a specific second sub question")
    public static void verifyCorrectGradeForASpecificSecondSubQuestion(int secondSubQuestionGradeFieldIndex, String expectedGrade) {
        Verifications.compareStringValues(GradingPage.secondSubQuestionGradeFieldList.get(secondSubQuestionGradeFieldIndex)
                .getAttribute("value"), expectedGrade);
    }

    @Step("Verify correct garde was given after using V annotation")
    public static void verifyCorrectGradeForAQuestionUsingVAnnotation(int questionGradeFieldIndex, int maxGradeListIndex) {
        Verifications.compareStringValues(GradingPage.questionGradeFieldList.get(questionGradeFieldIndex).getAttribute("value"),
                GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText());
    }

    @Step("Verify correct garde was given after using crossed V annotation")
    public static void verifyCorrectGradeForAQuestionUsingCrossedVAnnotation(int questionGradeFieldIndex, int maxGradeListIndex) {

        double halfOfMaxPossibleGradeD = Double.parseDouble(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        int halfOfMaxPossibleGradeI = Integer.parseInt(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        if ((halfOfMaxPossibleGradeD - (int) halfOfMaxPossibleGradeD) != 0)
            Verifications.compareStringValues(GradingPage.questionGradeFieldList.get(questionGradeFieldIndex).getAttribute("value"),
                    String.valueOf(halfOfMaxPossibleGradeD));
        else
            Verifications.compareStringValues(GradingPage.questionGradeFieldList.get(questionGradeFieldIndex).getAttribute("value"),
                    String.valueOf(halfOfMaxPossibleGradeI));
    }

    @Step("Verify correct grade was given after using V annotation")
    public static void verifyCorrectGradeForASubQuestionUsingVAnnotation(int subQuestionGradeFieldIndex, int maxGradeListIndex) {
        Verifications.compareStringValues(GradingPage.subQuestionGradeFieldList.get(subQuestionGradeFieldIndex)
                        .getAttribute("value"),
                GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText());
    }

    @Step("Verify correct grade was given after using crossed V annotation")
    public static void verifyCorrectGradeForASubQuestionUsingCrossedVAnnotation(int subQuestionGradeFieldIndex,
                                                                                int maxGradeListIndex) {
        double halfOfMaxPossibleGradeD = Double.parseDouble(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        int halfOfMaxPossibleGradeI = Integer.parseInt(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        if ((halfOfMaxPossibleGradeD - (int) halfOfMaxPossibleGradeD) != 0)
            Verifications.compareStringValues(GradingPage.subQuestionGradeFieldList.get(subQuestionGradeFieldIndex)
                    .getAttribute("value"), String.valueOf(halfOfMaxPossibleGradeD));
        else Verifications.compareStringValues(GradingPage.subQuestionGradeFieldList.get(subQuestionGradeFieldIndex)
                .getAttribute("value"), String.valueOf(halfOfMaxPossibleGradeI));

    }

    @Step("Verify correct garde was given after using V annotation")
    public static void verifyCorrectGradeForASecondSubQuestionUsingVAnnotation(int secondSubQuestionGradeFieldIndex,
                                                                               int maxGradeListIndex) {
        Verifications.compareStringValues(GradingPage.secondSubQuestionGradeFieldList.get(secondSubQuestionGradeFieldIndex)
                .getAttribute("value"), GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText());
    }

    @Step("Verify correct garde was given after using crossed V annotation")
    public static void verifyCorrectGradeForASecondSubQuestionUsingCrossedVAnnotation(int secondSubQuestionGradeFieldIndex,
                                                                                      int maxGradeListIndex) {
        double halfOfMaxPossibleGradeD = Double.parseDouble(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        int halfOfMaxPossibleGradeI = Integer.parseInt(GradingPage.questionsMaxPossibleGradeList.get(maxGradeListIndex).getText()) / 2;
        if ((halfOfMaxPossibleGradeD - (int) halfOfMaxPossibleGradeD) != 0)
            Verifications.compareStringValues(GradingPage.secondSubQuestionGradeFieldList.get(secondSubQuestionGradeFieldIndex)
                    .getAttribute("value"), String.valueOf(halfOfMaxPossibleGradeD));
        else
            Verifications.compareStringValues(GradingPage.secondSubQuestionGradeFieldList.get(secondSubQuestionGradeFieldIndex)
                    .getAttribute("value"), String.valueOf(halfOfMaxPossibleGradeI));

    }

    //---Grading flows---
    @Step("Grade using calculator buttons")
    public static void gradeUsingCalculatorButtons(String grade) {
        UIActions.click(GradingPage.gradeInputField);
        for (WebElement elem : GradingPage.calcButtonsList) {
            for (int i = 0; i < grade.length(); i++) {
                if (elem.getText().equalsIgnoreCase(String.valueOf(grade.charAt(i))))
                    UIActions.click(elem);
            }
        }
    }

    //TODO - Add verification of circle annotation visibility
    @Step("grade Max Possible Points Using GAnnotation")
    public static void gradeMaxPossiblePointsUsingMainGAnnotationAndCalcButtons(int pageNumber, String questionNumber,
                                                                                String comment, int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        UIActions.mouseHoverOnElement(AnnotationPage.gAnnotatorMainButton);
        UIActions.click(AnnotationPage.gAnnotatorMainButton);
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        GradingProcessFlows.selectQuestionToGradeForGAnnotations(questionNumber);
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        String maxPossibleGrade = GradingPage.maximumGradeForCurrentQuestion.getText();
        System.out.println("Max possible grade is " + maxPossibleGrade);
        GradingProcessFlows.gradeUsingCalculatorButtons(maxPossibleGrade);
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of grade text annotations for this student is " + GradingPage.gradeTextAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
    }

    //TODO - Add verification of circle annotation visibility
    @Step("grade Using G Annotation")
    public static void gradeUsingGMainAnnotation(int pageNumber, String questionNumber, double grade, String comment,
                                                 int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        UIActions.mouseHoverOnElement(AnnotationPage.gAnnotatorMainButton);
        UIActions.click(AnnotationPage.gAnnotatorMainButton);
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        GradingProcessFlows.selectQuestionToGradeForGAnnotations(questionNumber);
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        UIActions.setText(GradingPage.gradeInputField, String.valueOf(grade));
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of grade text annotations for this student is " + GradingPage.gradeTextAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
    }

    //TODO - Add verification of circle annotation visibility
    @Step("grade Using G Annotation")
    public static void gradeUsingRightGAnnotationIcon(int pageNumber, int gIconIndex, double grade, String comment,
                                                      int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(gIconIndex));
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        UIActions.setText(GradingPage.gradeInputField, String.valueOf(grade));
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of grade text annotations for this student is " + GradingPage.gradeTextAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
    }


    //TODO - Add verification of circle annotation visibility
    @Step("grade Using inner G Annotation")
    public static void gradeUsingInnerGAnnotation(int pageNumber, String questionNumber, double grade, String comment,
                                                  int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        if (AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.gAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.gAnnotatorContainer);
        } else if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.vAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.vAnnotatorContainer);
        } else if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.xAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.xAnnotatorContainer);
        } else if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.crossedVAnnotatorContainer);
        } else if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.noAnswerAnnotatorContainer);
        } else Assert.fail("Something went wrong - failing the test case");
        UIActions.click(AnnotationPage.innerGAnnotatorButton);
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        GradingProcessFlows.selectQuestionToGradeForGAnnotations(questionNumber);
        wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
        UIActions.setText(GradingPage.gradeInputField, String.valueOf(grade));
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of annotations for this student is " + GradingPage.circleGradeAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
    }

    @Step("grade full points using V Annotation")
    public static void gradeFullPointsUsingVAnnotation(int rightSideGAnnotationIndex, int pageNumber)
            throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(rightSideGAnnotationIndex));
        if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
            UIActions.click(AnnotationPage.vAnnotatorMainButton);
        } else if (AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.gAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.gAnnotatorContainer);
            UIActions.click(AnnotationPage.innerVAnnotatorButton);
        } else if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.xAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.xAnnotatorContainer);
            UIActions.click(AnnotationPage.innerVAnnotatorButton);
        } else if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.click(AnnotationPage.innerVAnnotatorButton);
        } else if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.click(AnnotationPage.innerVAnnotatorButton);
        } else Assert.fail("Something went wrong - failing the test case");
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
    }

    @Step("grade half points using crossed V Annotation")
    public static void gradeHalfPointsUsingCrossedVAnnotation(int rightSideGAnnotationIndex, int pageNumber)
            throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(rightSideGAnnotationIndex));
        if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
            UIActions.click(AnnotationPage.crossedVAnnotatorMainButton);
        } else if (AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.gAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.gAnnotatorContainer);
            UIActions.click(AnnotationPage.innerCrossedVAnnotatorButton);
        } else if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.vAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.vAnnotatorContainer);
            UIActions.click(AnnotationPage.innerCrossedVAnnotatorButton);
        } else if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.xAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.xAnnotatorContainer);
            UIActions.click(AnnotationPage.innerCrossedVAnnotatorButton);
        } else if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.click(AnnotationPage.innerCrossedVAnnotatorButton);
        } else Assert.fail("Something went wrong - failing the test case");
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
    }

    @Step("Grading 0 points using X Annotation")
    public static void gradeZeroPointsUsingXAnnotation(int rightSideGAnnotationIndex, int pageNumber)
            throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(rightSideGAnnotationIndex));
        if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
            UIActions.click(AnnotationPage.xAnnotatorMainButton);
        } else if (AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.gAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.gAnnotatorContainer);
            UIActions.click(AnnotationPage.innerXAnnotatorButton);
        } else if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.vAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.vAnnotatorContainer);
            UIActions.click(AnnotationPage.innerXAnnotatorButton);
        } else if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.click(AnnotationPage.innerXAnnotatorButton);
        } else if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.noAnswerAnnotatorContainer);
            UIActions.click(AnnotationPage.innerXAnnotatorButton);
        } else Assert.fail("Something went wrong - failing the test case");
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
    }

    @Step("Indicate no answer using designated annotation")
    public static void IndicateNoAnswer(int rightSideGAnnotationIndex, int pageNumber, String qType)
            throws InterruptedException {
        List<Integer> currentNoAnswerIndication = new ArrayList();
        currentNoAnswerIndication.add(0, GradingPage.noAnswerQuestionCell.size());
        currentNoAnswerIndication.add(1, GradingPage.noAnswerSubQuestionCell.size());
        currentNoAnswerIndication.add(2, GradingPage.noAnswerSecondSubQuestionCell.size());
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(rightSideGAnnotationIndex));
        if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
            UIActions.click(AnnotationPage.noAnswerAnnotatorMainButton);
        } else if (AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.gAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.gAnnotatorContainer);
            UIActions.click(AnnotationPage.innerNoAnswerAnnotatorButton);
        } else if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.vAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.vAnnotatorContainer);
            UIActions.click(AnnotationPage.innerNoAnswerAnnotatorButton);
        } else if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.xAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.xAnnotatorContainer);
            UIActions.click(AnnotationPage.innerNoAnswerAnnotatorButton);
        } else if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
            UIActions.moveToElement(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.leftClickAndHold(AnnotationPage.crossedVAnnotatorContainer);
            UIActions.click(AnnotationPage.innerNoAnswerAnnotatorButton);
        } else Assert.fail("Something went wrong - failing the test case");
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1000);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        if (qType.equalsIgnoreCase("question")) {
            int numOfUnansweredQuestions = currentNoAnswerIndication.get(0) + 1;
            System.out.println("The total number of not answered questions is " + numOfUnansweredQuestions);
            Verifications.compareIntValues(GradingPage.noAnswerQuestionCell.size(), numOfUnansweredQuestions);
        } else if (qType.equalsIgnoreCase("subQuestion")) {
            int numOfUnansweredSubQuestions = currentNoAnswerIndication.get(1) + 1;
            System.out.println("The total number of not answered questions is " + numOfUnansweredSubQuestions);
            Verifications.compareIntValues(GradingPage.noAnswerSubQuestionCell.size(), numOfUnansweredSubQuestions);
        } else if (qType.equalsIgnoreCase("secondSubQuestion")) {
            int numOfUnansweredSecondSubQuestions = currentNoAnswerIndication.get(2) + 1;
            System.out.println("The total number of not answered questions is " + numOfUnansweredSecondSubQuestions);
            Verifications.compareIntValues(GradingPage.noAnswerSecondSubQuestionCell.size(), numOfUnansweredSecondSubQuestions);
        }
    }

    public static void gradeMaxPointsUsingPlusAnnotationAndTypeGrade(int pageNumber, String questionNumber, String comment,
                                                                     int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        int initialNumOfSavedTextComments = GradingPage.savedCommentsRowList.size();
        UIActions.mouseHoverOnElement(AnnotationPage.plusGradeAnnotator);
        UIActions.click(AnnotationPage.plusGradeAnnotator);
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        wait.until(ExpectedConditions.visibilityOf(GradingPage.plusOrMinusGradingRangeText));
        UIActions.moveToElement(GradingPage.targetQuestionSelector);
        GradingProcessFlows.selectQuestionToGradeForPlusOrMinusAnnotations(questionNumber);
        wait.until(ExpectedConditions.visibilityOf(GradingPage.plusOrMinusGradingRangeText));
        String gradingRangeText = GradingPage.plusOrMinusGradingRangeText.getText();
        String[] gradingRange = gradingRangeText.split(" ");
        double maxGrade = Double.parseDouble(gradingRange[2]);
        UIActions.setText(GradingPage.gradeInputField, String.valueOf(maxGrade));
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of grade text annotations for this student is " + GradingPage.gradeTextAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.compareIntValues(GradingPage.savedCommentsRowList.size(), initialNumOfSavedTextComments + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
        Verifications.textInElement(GradingPage.savedTextCommentsList.get(0), comment);
    }

    public static void gradesUsingMinusAnnotationAndTypeGrade(int pageNumber, String questionNumber, int pointToSubtract, String comment,
                                                              int gradeTextAnnotationIndex) throws InterruptedException {
        int initialNumOfGradeText = GradingPage.gradeTextAnnotationList.size();
        int initialNumOfSavedTextComments = GradingPage.savedCommentsRowList.size();
        UIActions.mouseHoverOnElement(AnnotationPage.minusGradeAnnotator);
        UIActions.click(AnnotationPage.minusGradeAnnotator);
        Thread.sleep(500);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(1500);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        wait.until(ExpectedConditions.visibilityOf(GradingPage.plusOrMinusGradingRangeText));
        GradingProcessFlows.selectQuestionToGradeForPlusOrMinusAnnotations(questionNumber);
        wait.until(ExpectedConditions.visibilityOf(GradingPage.plusOrMinusGradingRangeText));
        UIActions.setText(GradingPage.gradeInputField, String.valueOf(pointToSubtract));
        UIActions.click(GradingPage.gradeCommentTextField);
        UIActions.setText(GradingPage.gradeCommentTextField, comment);
        UIActions.click(GradingPage.approveGradeButton);
        Thread.sleep(2500);
        try {
            UIActions.click(GradingPage.finishedGradingAllQuestionsAlertCloseButton);
        } catch (Exception e) {
            System.out.println("Not all questions were graded yet");
        }
        System.out.println("The number of grade text annotations for this student is " + GradingPage.gradeTextAnnotationList.size());
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumOfGradeText + 1);
        Verifications.compareIntValues(GradingPage.savedCommentsRowList.size(), initialNumOfSavedTextComments + 1);
        Verifications.visibilityOfElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex));
        Verifications.textInElement(GradingPage.gradeTextAnnotationList.get(gradeTextAnnotationIndex), comment);
        Verifications.textInElement(GradingPage.savedTextCommentsList.get(0), comment);
    }

    //--Selecting flows---
    @Step("Select a specific student's exam")
    public static void selectStudent(int targetStudentPosition) throws InterruptedException {
        String[] studentsToSelect = GradingPage.selectedStudentPosition.getText().split(" ");
        System.out.println("Currently selected student position is " + GradingPage.selectedStudentPosition.getText());
        System.out.println("Target student to select is " + targetStudentPosition);
        if (Integer.parseInt(studentsToSelect[1]) < targetStudentPosition) {
            while (Integer.parseInt(studentsToSelect[1]) < targetStudentPosition) {
                String previousUrl = UIActions.getCurrentURL();
                LocalDateTime currentTime = UsefulMethods.getPCDateTime("yyyy-MM-dd HH:mm:ss");
                UIActions.click(GradingPage.nextExamArrowButton);
                wait.until(ExpectedConditions.not(urlToBe(previousUrl)));
                LocalDateTime newTime = UsefulMethods.getPCDateTime("yyyy-MM-dd HH:mm:ss");
                studentsToSelect = GradingPage.selectedStudentPosition.getText().split(" ");
                String transitionTimeInMilliSeconds = UsefulMethods.calculateTimeDifference(currentTime, newTime);
                if (Integer.parseInt(transitionTimeInMilliSeconds) > 5000)
                    Assert.fail("Transition time between 2 students was larger than 5 seconds - failing this test case");
                Thread.sleep(2000);
                if (Integer.parseInt(studentsToSelect[1]) == targetStudentPosition) {
                    System.out.println("Reached target student");
                    break;
                }
            }
        } else if (Integer.parseInt(studentsToSelect[1]) > targetStudentPosition) {
            while (Integer.parseInt(studentsToSelect[1]) > targetStudentPosition) {
                String previousUrl = UIActions.getCurrentURL();
                LocalDateTime currentTime = UsefulMethods.getPCDateTime("yyyy-MM-dd HH:mm:ss");
                UIActions.click(GradingPage.previousExamArrowButton);
                wait.until(ExpectedConditions.not(urlToBe(previousUrl)));
                LocalDateTime newTime = UsefulMethods.getPCDateTime("yyyy-MM-dd HH:mm:ss");
                studentsToSelect = GradingPage.selectedStudentPosition.getText().split(" ");
                String transitionTimeInMilliSeconds = UsefulMethods.calculateTimeDifference(currentTime, newTime);
                if (Integer.parseInt(transitionTimeInMilliSeconds) > 5000)
                    Assert.fail("Transition time between 2 students was larger than 5 seconds - failing this test case");
                Thread.sleep(2000);
                if (Integer.parseInt(studentsToSelect[1]) == targetStudentPosition) {
                    System.out.println("Reached target position");
                    break;
                }
            }
        } else System.out.println("Target student is already selected");
    }

    @Step("Select question to grade for G annotations")
    public static void selectQuestionToGradeForGAnnotations(String questionNumber) {
        System.out.println("Current selected question to grade is " + GradingPage.targetQuestionSelector.getAttribute("value"));
        System.out.println("Target Question is " + questionNumber);
        if (!GradingPage.targetQuestionSelector.getAttribute("value").equalsIgnoreCase(questionNumber)) {
            for (int i = 0; i < GradingPage.smallGIconsList.size(); i++) {
                if (!GradingPage.targetQuestionSelector.getAttribute("value").equalsIgnoreCase(questionNumber)) {
                    UIActions.click(GradingPage.nextQuestionArrow);
                    wait.until(ExpectedConditions.visibilityOf(GradingPage.maximumGradeForCurrentQuestion));
                }
            }
        }
    }

    @Step("Select question to grade for plus or minus annotations")
    public static void selectQuestionToGradeForPlusOrMinusAnnotations(String questionNumber) {
        System.out.println("Current selected question to grade is " + GradingPage.targetQuestionSelector.getAttribute("value"));
        System.out.println("Target Question is " + questionNumber);
        if (!GradingPage.targetQuestionSelector.getAttribute("value").equalsIgnoreCase(questionNumber)) {
            for (int i = 0; i < GradingPage.smallGIconsList.size(); i++) {
                if (!GradingPage.targetQuestionSelector.getAttribute("value").equalsIgnoreCase(questionNumber)) {
                    UIActions.click(GradingPage.nextQuestionArrow);
                    wait.until(ExpectedConditions.visibilityOf(GradingPage.plusOrMinusGradingRangeText));
                }
            }
        }
    }

    @Step("Select G annotator container")
    public static void verifyGAnnotatorContainerIsSelected() {
        if (!AnnotationPage.gAnnotatorMainButton.isDisplayed()) {
            if (AnnotationPage.vAnnotatorMainButton.isDisplayed()) {
                UIActions.moveToElement(AnnotationPage.vAnnotatorContainer);
                UIActions.leftClickAndHold(AnnotationPage.vAnnotatorContainer);
                UIActions.click(AnnotationPage.innerGAnnotatorButton);
            }
            if (AnnotationPage.xAnnotatorMainButton.isDisplayed()) {
                UIActions.moveToElement(AnnotationPage.xAnnotatorContainer);
                UIActions.leftClickAndHold(AnnotationPage.xAnnotatorContainer);
                UIActions.click(AnnotationPage.innerGAnnotatorButton);
            }
            if (AnnotationPage.crossedVAnnotatorMainButton.isDisplayed()) {
                UIActions.moveToElement(AnnotationPage.crossedVAnnotatorContainer);
                UIActions.leftClickAndHold(AnnotationPage.crossedVAnnotatorContainer);
                UIActions.click(AnnotationPage.innerGAnnotatorButton);
            }
            if (AnnotationPage.noAnswerAnnotatorMainButton.isDisplayed()) {
                UIActions.moveToElement(AnnotationPage.noAnswerAnnotatorContainer);
                UIActions.leftClickAndHold(AnnotationPage.noAnswerAnnotatorContainer);
                UIActions.click(AnnotationPage.innerGAnnotatorButton);
            }
        } else System.out.println("GAnnotator Main Button is already selected");
        Verifications.visibilityOfElement(AnnotationPage.gAnnotatorMainButton);
    }

    //---Deleting flows---
    @Step("Delete a specific gradeText annotation")
    public static void deleteSpecificGradeTextAnnotation(int pageNumber, int textAnnotationIndex) {
        int initialNumberOfGradeText = GradingPage.gradeTextAnnotationList.size();
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.mouseHoverOnElement(GradingPage.gradeTextAnnotationList.get(textAnnotationIndex));
        UIActions.click(GradingPage.gradeTextAnnotationList.get(textAnnotationIndex));
        UIActions.click(AnnotationPage.deleteAnnotationButton);
        Verifications.compareIntValues(GradingPage.gradeTextAnnotationList.size(), initialNumberOfGradeText - 1);
    }

    //TODO - Make it happen....
    @Step("Delete a specific gradeCircle annotation")
    public static void deleteSpecificGradeCircleAnnotation(int pageNumber, int circleAnnotationIndex) {
    }

    @Step("Delete a specific question's grade")
    public static void deleteSpecificQuestionGrade(int gIconIndex, int questionIndex) throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(gIconIndex));
        Thread.sleep(1000);
        UIActions.click(AnnotationPage.deleteAnnotationButton);
        Verifications.verifyElementIsEmpty(GradingPage.questionGradeFieldList.get(questionIndex));
    }

    @Step("Delete a specific sub question's grade")
    public static void deleteSpecificSubQuestionGrade(int gIconIndex, int subQuestionIndex) throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(gIconIndex));
        Thread.sleep(1000);
        UIActions.click(AnnotationPage.deleteAnnotationButton);
        Verifications.verifyElementIsEmpty(GradingPage.subQuestionGradeFieldList.get(subQuestionIndex));
    }

    @Step("Delete a specific second sub question's grade")
    public static void deleteSpecificSecondSubQuestionGrade(int gIconIndex, int secondSubQuestionIndex) throws InterruptedException {
        UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(gIconIndex));
        Thread.sleep(1000);
        UIActions.click(AnnotationPage.deleteAnnotationButton);
        Verifications.verifyElementIsEmpty(GradingPage.secondSubQuestionGradeFieldList.get(secondSubQuestionIndex));
    }

    @Step("Delete all manually given grades and text comments for all students")
    public static void deleteAllManuallyGivenGradesAndCommentsFromAllStudents() throws InterruptedException {
        int studentIndex = 1;
        String[] studentsToSelect = GradingPage.selectedStudentPosition.getText().split(" ");
        int numOfStudents = Integer.parseInt(studentsToSelect[3]);
        while (studentIndex <= numOfStudents) {
            GradingProcessFlows.selectStudent(studentIndex);
            List<WebElement> questionGradeFieldsToDelete = new ArrayList();
            questionGradeFieldsToDelete.add(0, GradingPage.questionGradeFieldList.get(0));
            questionGradeFieldsToDelete.add(1, GradingPage.questionGradeFieldList.get(3));
            List<WebElement> subQuestionGradeFieldsToDelete = new ArrayList();
            if (GradingPage.notAnsweredSubQuestionGradeFieldList.size() > 0)
                subQuestionGradeFieldsToDelete.add(0, GradingPage.notAnsweredSubQuestionGradeFieldList.get(0));
            else subQuestionGradeFieldsToDelete.add(0, GradingPage.subQuestionGradeFieldList.get(0));
            List<WebElement> secondSubQuestionGradeFieldsToDelete = new ArrayList();
            if (GradingPage.notAnsweredSecondSubQuestionGradeFieldList.size() == 0) {
                secondSubQuestionGradeFieldsToDelete.add(0, GradingPage.secondSubQuestionGradeFieldList.get(0));
                secondSubQuestionGradeFieldsToDelete.add(1, GradingPage.secondSubQuestionGradeFieldList.get(1));
            } else if (GradingPage.notAnsweredSecondSubQuestionGradeFieldList.size() == 2) {
                secondSubQuestionGradeFieldsToDelete.add(0, GradingPage.notAnsweredSecondSubQuestionGradeFieldList.get(0));
                secondSubQuestionGradeFieldsToDelete.add(1, GradingPage.notAnsweredSecondSubQuestionGradeFieldList.get(1));
            } else {
                try {
                    secondSubQuestionGradeFieldsToDelete.add(0, GradingPage.secondSubQuestionGradeFieldList.get(0));
                    secondSubQuestionGradeFieldsToDelete.add(1, GradingPage.notAnsweredSecondSubQuestionGradeFieldList.get(0));
                } catch (Exception e) {
                    secondSubQuestionGradeFieldsToDelete.add(0, GradingPage.notAnsweredSecondSubQuestionGradeFieldList.get(0));
                    secondSubQuestionGradeFieldsToDelete.add(1, GradingPage.secondSubQuestionGradeFieldList.get(0));
                }
            }
            List<Integer> questionGIconsIndexes = new ArrayList();
            questionGIconsIndexes.add(0, 0);
            questionGIconsIndexes.add(1, 6);
            List<Integer> subQuestionGIconsIndexes = new ArrayList();
            subQuestionGIconsIndexes.add(0, 3);
            List<Integer> secondSubQuestionGIconsIndexes = new ArrayList();
            secondSubQuestionGIconsIndexes.add(0, 4);
            secondSubQuestionGIconsIndexes.add(1, 5);

            for (int i = 0; i < questionGradeFieldsToDelete.size(); i++) {
                UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(questionGIconsIndexes.get(i)));
                Thread.sleep(1000);
                UIActions.click(AnnotationPage.deleteAnnotationButton);
                Thread.sleep(1000);
            }
            Verifications.verifyElementsAreEmpty(GradingPage.notAnsweredQuestionGradeFieldList);
            Verifications.verifyElementsAreEmpty(questionGradeFieldsToDelete);
            for (int i = 0; i < subQuestionGradeFieldsToDelete.size(); i++) {
                UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(subQuestionGIconsIndexes.get(i)));
                Thread.sleep(1000);
                UIActions.click(AnnotationPage.deleteAnnotationButton);
                Thread.sleep(1000);
            }
            Verifications.verifyElementsAreEmpty(GradingPage.notAnsweredSubQuestionGradeFieldList);
            Verifications.verifyElementsAreEmpty(GradingPage.subQuestionGradeFieldList);
            for (int i = 0; i < secondSubQuestionGradeFieldsToDelete.size(); i++) {
                UIActions.click(GradingPage.rightSideGAnnotationIconsList.get(secondSubQuestionGIconsIndexes.get(i)));
                Thread.sleep(1000);
                UIActions.click(AnnotationPage.deleteAnnotationButton);
                Thread.sleep(1000);
            }
            Verifications.verifyElementsAreEmpty(GradingPage.notAnsweredSecondSubQuestionGradeFieldList);
            Verifications.verifyElementsAreEmpty(GradingPage.secondSubQuestionGradeFieldList);
            if (GradingPage.gradeTextAnnotationList.size() > 0) {
                for (WebElement gradeText : GradingPage.gradeTextAnnotationList) {
                    UIActions.click(gradeText);
                    UIActions.click(AnnotationPage.deleteAnnotationButton);
                }
                System.out.println("Finished deleting all grade texts comments for this student");
            } else System.out.println("There are no grade text comments to delete for this student");
            Verifications.nonVisibilityOfAllElementsOfAList(GradingPage.gradeTextAnnotationList);
            if (GradingPage.textAnnotationList.size() > 0) {
                for (WebElement textComment : GradingPage.textAnnotationList) {
                    UIActions.click(textComment);
                    UIActions.click(AnnotationPage.deleteAnnotationButton);
                }
                System.out.println("Finished deleting all text comments for this student");
            } else System.out.println("There are no text comments to delete for this student");
            Verifications.nonVisibilityOfAllElementsOfAList(GradingPage.textAnnotationList);
            studentIndex = studentIndex + 1;
            if (studentIndex > Integer.parseInt(studentsToSelect[3])) {
                System.out.println("Finished deleting manually given grades for all questions");
                break;
            }
        }
    }

    @Step("Add a text comment")
    public static void addATextComment(int pageNumber, String textComment) throws InterruptedException {
        int initialNumOfDisplayedTextComments = GradingPage.textAnnotationList.size();
        int initialNumOfSavedTextComments = GradingPage.savedCommentsRowList.size();
        System.out.println("Initial number of displayed text annotation is " + GradingPage.textAnnotationList.size());
        System.out.println("Initial number of saved text annotation is " + GradingPage.savedCommentsRowList.size());
        UIActions.moveToElement(AnnotationPage.textCommentButton);
        UIActions.click(AnnotationPage.textCommentButton);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.textAnnotationBoxesList.get(GradingPage.textAnnotationBoxesList.size() - 1));
        wait.until(ExpectedConditions.attributeToBe(GradingPage.textAnnotationBoxesList.get(GradingPage.textAnnotationBoxesList.size() - 1),
                "data-helper-active", "true"));
        UIActions.updateText(GradingPage.textAnnotationBoxesList.get(GradingPage.textAnnotationBoxesList.size() - 1), textComment);
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Thread.sleep(2000);
        System.out.println("Updated number of displayed text annotation is " + GradingPage.textAnnotationList.size());
        System.out.println("Updated number of saved text annotation is " + GradingPage.savedCommentsRowList.size());
        Verifications.textInElement(GradingPage.textAnnotationList.get(GradingPage.textAnnotationList.size() - 1), textComment);
        Verifications.compareIntValues(GradingPage.textAnnotationList.size(), initialNumOfDisplayedTextComments + 1);
        Verifications.textInElement(GradingPage.savedTextCommentsList.get(0), textComment);
        Verifications.compareIntValues(GradingPage.savedCommentsRowList.size(), initialNumOfSavedTextComments + 1);

    }

    @Step("Add a sticky note comment")
    public static void addAStickNoteComment(int pageNumber, String textComment) throws InterruptedException {
        int currentNumOfStickyNoteComments; // TODO - Define a list size
        int initialTotalNumOfSavedTextComments = GradingPage.savedCommentsRowList.size();
        UIActions.moveToElement(AnnotationPage.stickyCommentButton);
        UIActions.click(AnnotationPage.stickyCommentButton);
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.stickyNoteTextArea);
        UIActions.updateText(GradingPage.stickyNoteTextArea, textComment);
        UIActions.click(GradingPage.stickyNoteApproveButton);
        Thread.sleep(2000);
        //TODO - Verify the size of sticky notes has grown in one and verify the sticky note's hidden text
        Verifications.compareIntValues(GradingPage.savedCommentsRowList.size(), initialTotalNumOfSavedTextComments + 1);
        Verifications.textInElement(GradingPage.savedTextCommentsList.get(0), textComment);
    }


    @Step("Delete all saved text comments")
    public static void deleteAllSavedTextComments() {
        if (GradingPage.savedCommentsRowList.size() > 0) {
            for (WebElement elem : GradingPage.savedTextCommentsTrashIconList) {
                elem.click();
            }
            System.out.println("Finished deleting all saved text comments");
        } else System.out.println("There are no saved text comment for this exam");
        wait.until(ExpectedConditions.invisibilityOfAllElements(GradingPage.savedCommentsRowList));
        Verifications.verifyWebElementsListSize(GradingPage.savedCommentsRowList, 0);
    }

    //---General infrastructure flows---
    @Step("Scroll down to a specific page")
    public static void scrollToSpecificPage(int destinationPage) {
        UIActions.click(GradingPage.canvasPagesList.get(0));
        UIActions.moveToElement(GradingPage.canvasPagesList.get(destinationPage));
    }

    @Step("calculate canvas center")
    public static void calculateCanvasCenter(int pageNumber) throws InterruptedException {
        UIActions.moveToElement(GradingPage.canvasPagesList.get(pageNumber));
        UIActions.click(GradingPage.canvasPagesList.get(pageNumber));
        Dimension canvasDimensions = GradingPage.canvasPagesList.get(pageNumber).getSize();
        System.out.println(canvasDimensions);
        int canvasCenterX = canvasDimensions.getWidth()/2;
        System.out.println(canvasCenterX);
        int canvasCenterY = canvasDimensions.getHeight()/2;
        System.out.println(canvasCenterY);
        int offsetX = (canvasCenterX/3);
        int offsetY = (canvasCenterY/3);
        action.moveToElement(GradingPage.canvasPagesList.get(pageNumber)).moveByOffset(offsetX,offsetY).build().perform();
        int newXPos = canvasCenterX+offsetX;
        int newYPos = canvasCenterY+offsetY;
        System.out.println("New coordinates are  " + newXPos + "," + newYPos);
        Thread.sleep(2000);
    }

}


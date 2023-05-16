package workFlows.webFlows.TomaGrade;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import utilities.CommonOperations;

public class GradeArrayFlows extends CommonOperations {

    @Step("Verify some additional empty grade array properties")
    public static void verifyAdditionalEmptyGradeArrayProperties() {
        Verifications.textInElement(GradeArrayPage.noExistingGradeArrayTxt, "לא קיים מערך ציונים");
        Verifications.textInElement(GradeArrayPage.additionalScaleCheckboxTxt, "הוספת סולם ציונים נוסף עבור הציון הסופי");
        Verifications.textInElement(GradeArrayPage.gardeArrayCenterTitle, "מערך ציונים - שלב מקדים לבדיקה");
        Verifications.textInElement(GradeArrayPage.gardeArrayCenterGuidance, "הכנס את הציון המקסימלי עבור כל שאלה/ סעיף, לדוגמא 1.1");
    }

    @Step("Verify selected course details")
    public static void createNewGradeArrayWithEqualScoring(int numOfQuestions, int scorePerQuestion) {
        if (GradeArrayPage.noExistingGradeArrayTxt.isDisplayed()) {
            UIActions.click(HomePage.gradeArrayButton);
            UIActions.setText(GradeArrayPage.numOfQuestionsTxtField, String.valueOf(numOfQuestions));
            UIActions.setText(GradeArrayPage.scorePerQuestionTxtField, String.valueOf(scorePerQuestion));
            UIActions.click(GradeArrayPage.addRowsButton);
        } else System.out.println("A grade array already exist");
    }

    @Step("Verify some additional empty grade array properties")
    public static void createEqualPointsGradeArray(String desiredQuestionType) {
        if (!GradeArrayPage.questionTypeSelectedOption.getText().equalsIgnoreCase(desiredQuestionType))
            UIActions.selectByVisibleText(GradeArrayPage.questionTypeDropDownBtn, desiredQuestionType);
    }

    @Step("Verify course has no predefined grade array")
    public static void verifyEmptyGradeArray(String noGradeCourseName) {
        UIActions.setText(HomePage.uncheckedCoursesSearchField, noGradeCourseName);
        UIActions.clickEnter(HomePage.uncheckedCoursesSearchField);
        UIActions.click(HomePage.pendingCoursesNamesList.get(0));
        UIActions.click(HomePage.gradeArrayButton);
        Verifications.GradeArrayPage.noExistingGradeArrayTxt.isDisplayed();
        Verifications.notSelectedElement(GradeArrayPage.additionalScaleCheckbox);
    }


}

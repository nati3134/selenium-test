package workFlows.webFlows.TomaGrade;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import utilities.CommonOperations;

import static utilities.UsefulMethods.getData;

public class LoginFlows extends CommonOperations {

    @Step("Verify valid credentials are required for TomaGrade login")
    public static void verifyWrongOrMissingCredentialsAlerts(String email, String wrongPassword) {
        UIActions.click(GradeLoginPage.emailField);
        UIActions.clearText(GradeLoginPage.emailField);
        UIActions.click(GradeLoginPage.passwordField);
        UIActions.clearText(GradeLoginPage.passwordField);
        UIActions.click(GradeLoginPage.loginButton);
        Verifications.visibilityOfElement(GradeLoginPage.missingOrInvalidCredentialsAlert);
        UIActions.click(GradeLoginPage.closeAlertButton);
        UIActions.setText(GradeLoginPage.emailField, email);
        UIActions.click(GradeLoginPage.passwordField);
        UIActions.setText(GradeLoginPage.passwordField, wrongPassword);
        UIActions.click(GradeLoginPage.loginButton);
        Verifications.visibilityOfElement(GradeLoginPage.missingOrInvalidCredentialsAlert);
        UIActions.click(GradeLoginPage.closeAlertButton);
    }

    @Step("Sign In")
    public static void tomaGradeSignIn() {
        String email = getData("loginEmailAddress");
        String password = getData("loginPassword");
        String expectedLecturer = getData("lecturerName");
        if (System.getenv("env_user") == null) {
            email = getData("loginEmailAddressLinux");
            password = getData("loginPasswordLinux");
            expectedLecturer = getData("lecturerNameLinux");
        }
        UIActions.click(GradeLoginPage.emailField);
        UIActions.setText(GradeLoginPage.emailField, email);
        UIActions.click(GradeLoginPage.passwordField);
        UIActions.setText(GradeLoginPage.passwordField, password);
        UIActions.click(GradeLoginPage.loginButton);
        try {
            if (HomePage.randomQuestionDialog.isDisplayed())
                UIActions.click(HomePage.randomQuestionDialogCloseButton);
        } catch (Exception e) {
            System.out.println("Random question dialog is not displayed" + e);
        }
        try {
            if (HomePage.welcomeAlert.isDisplayed()) {
                UIActions.click(HomePage.greenCloseAlertButton);
            }
        } catch (Exception e) {
            System.out.println("Home alert dialog is not displayed" + e);
        }
        Verifications.compareStringValues(HomePage.lecturerName.getText(), expectedLecturer);
        Verifications.visibilityOfElement(HomePage.lastLoginDate);
    }
}

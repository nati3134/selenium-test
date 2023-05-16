package pageObjects.tomaGrade;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TomaGradeLoginPage {

    @FindBy(how = How.ID, using = "exampleInputEmail1")
    public WebElement emailField;
    @FindBy(how = How.CSS, using = "button.btn")
    public WebElement loginButton;
    @FindBy(how = How.ID, using = "exampleInputPassword1")
    public WebElement passwordField;
    @FindBy(how = How.CSS, using = "div.modal-content div.modal-content")
    public WebElement missingOrInvalidCredentialsAlert;
    @FindBy(how = How.CSS, using = "button.close")
    public WebElement closeAlertButton;
}

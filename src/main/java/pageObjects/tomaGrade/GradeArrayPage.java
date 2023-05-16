package pageObjects.tomaGrade;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GradeArrayPage {

    @FindBy(how = How.ID, using = "numberQuestionsToAdd4")
    public WebElement numOfQuestionsTxtField;
    @FindBy(how = How.ID, using = "questionScoreArray")
    public WebElement scorePerQuestionTxtField;
    @FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'addRow')]")
    public WebElement addRowsButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'addChoosingGroups')]")
    public WebElement groupSelectionButton;
    @FindBy(how = How.CSS, using = "button[ng-click='openImportGradeArray()']")
    public WebElement importGradeArrayButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'save-btn')]")
    public WebElement saveButton;
    @FindBy(how = How.CSS, using = "grading-scale.ng-scope input")
    public WebElement additionalScaleCheckbox;
    @FindBy(how = How.CSS, using = "grading-scale.ng-scope span")
    public WebElement additionalScaleCheckboxTxt;
    @FindBy(how = How.CSS, using = "strong div.text-center")
    public WebElement noExistingGradeArrayTxt;
    @FindBy(how = How.ID, using = "GradeArrayTotalGrade")
    public WebElement totalAchievableScoreIndication;
    @FindBy(how = How.CSS, using = "input.question-checkbox")
    public List<WebElement> questionCheckboxesList;
    @FindBy(how = How.CSS, using = "span.tx-num-icon")
    public List<WebElement> questionNumberList;
    @FindBy(how = How.CSS, using = "input[ng-change='updateGrade(questionRow)']")
    public List<WebElement> maxScorePerQuestionTxtFieldList;
    @FindBy(how = How.CSS, using = "input.DescriptionTextArea")
    public List<WebElement> questionDescriptionTxtFieldList;
    @FindBy(how = How.CSS, using = "input[ng-model='question.Description']")
    public WebElement questionDescriptionEditor;
    @FindBy(how = How.ID, using = "cofirm_green_button")
    public WebElement closeQuestionDescriptionEditorBtn;
    @FindBy(how = How.CSS, using = "input[ng-model='questionRow.IsBonus']")
    public List<WebElement> bonusQuestionCheckboxesList;
    @FindBy(how = How.CSS, using = "a.btn")
    public List<WebElement> deleteRowBtnList;
    @FindBy(how = How.ID, using = "single-button")
    public WebElement selectAdditionalScaleBtn;
    @FindBy(how = How.CSS, using = "div.info-tooltip-container span")
    public WebElement questionTypeTooltipContainer;
    @FindBy(how = How.CSS, using = "select.question-type-select option")
    public List<WebElement> questionTypeOptionList;
    @FindBy(how = How.CSS, using = "select.question-type-select")
    public WebElement questionTypeDropDownBtn;
    @FindBy(how = How.CSS, using = "select.question-type-select option[value='open']")
    public WebElement questionTypeSelectedOption;
    @FindBy(how = How.CSS, using = "div.col-lg-12 strong")
    public WebElement gardeArrayCenterTitle;
    @FindBy(how = How.CSS, using = "div.col-lg-12 i")
    public WebElement gardeArrayCenterGuidance;

}

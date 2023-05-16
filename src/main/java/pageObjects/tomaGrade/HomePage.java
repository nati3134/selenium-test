package pageObjects.tomaGrade;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage {

    @FindBy(how = How.CSS, using = "strong.ng-binding")
    public WebElement lastLoginDate;
    @FindBy(how = How.CSS, using = "div.topMainTabs img.examMainLbl")
    public WebElement tomaxIcon;
    @FindBy(how = How.CLASS_NAME, using = "userNameMainLbl")
    public WebElement lecturerName;
    @FindBy(how = How.ID, using = "gradesArrayStructure")
    public WebElement gradeArrayButton;
    @FindBy(how = How.ID, using = "startCheckingButton")
    public WebElement startGradingProcessButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'postPublishExamGrades')]")
    public WebElement endGradingProcessButton;
    @FindBy(how = How.CSS, using = "a[title='התנתק']")
    public WebElement signOutButton;
    @FindBy(how = How.CSS, using = "button.moreOperationsBtn")
    public WebElement otherOperationsButton;
    @FindBy(how = How.ID, using = "searchOpenCourse")
    public WebElement uncheckedCoursesSearchField;
    @FindBy(how = How.XPATH, using = "//div[@data-search-name='courses']")
    public WebElement uncheckedCoursesSearchFieldContainer;
    @FindBy(how = How.CSS, using = "div.finishedCoursesContainer div input")
    public WebElement finishedCoursesSearchField;
    @FindBy(how = How.CSS, using = "div[id='unchecked-courses'] span.InputItem")
    public WebElement selectedUncheckedCourse;
    @FindBy(how = How.CSS, using = "div.coursesContainer span[title='CleanFilter'] i")
    public WebElement cleanSelectedCourseFilterButton;
    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'main-Bottom-left-section')]/div[@class='rtl']/div)[1]")
    public WebElement leftSideGradingProgressIndicator;
    @FindBy(how = How.CSS, using = "div.CourseName b")
    public WebElement selectedCourseName;
    @FindBy(how = How.CSS, using = "div.CourseName")
    public WebElement selectedCourseTopLeftDetails;


    //--Lists---
    @FindBy(how = How.CSS, using = "div.main-top-left-operations div a")
    public List<WebElement> tomaGradeTopTabsList;
    @FindBy(how = How.CSS, using = "div.action-item div.helpMainBtn")
    public List<WebElement> actionButtonsList;
    @FindBy(how = How.XPATH, using = "//div[@class='helpMainBtn']/div/div")
    public List<WebElement> actionButtonsTextFirstFour;
    @FindBy(how = How.CSS, using = "ul.otherOperations li")
    public List<WebElement> otherOperationsList;
    @FindBy(how = How.CSS, using = "span.courseRow-courseName")
    public List<WebElement> pendingCoursesNamesList;
    @FindBy(how = How.CSS, using = "span.courseExamID")
    public List<WebElement> pendingCoursesIDList;
    @FindBy(how = How.CSS, using = "div.courseRow a.co div")
    public List <WebElement> courseDetailsUpperContainerList;
    @FindBy(how = How.CSS, using = "div.steps a.accessibility-item-dark")
    public List <WebElement> processStepsList;
    @FindBy(how = How.CSS, using = "div.steps li")
    public List <WebElement> processStepsGuidanceList;


    //---Popup alerts---
    @FindBy(how = How.CSS, using = "div.modal-body")
    public WebElement welcomeAlert;
    @FindBy(how = How.CSS, using = "button[title='סגור'] i")
    public WebElement closeWelcomeDialogIcon;
    @FindBy(how = How.CSS, using = "div.video-modal-footer  button[ng-click='$close(doNotShowVideo);']")
    public WebElement greenCloseAlertButton;
    @FindBy(how = How.ID, using = "do-not-show")
    public WebElement doNotShowCheckbox;
    @FindBy(how = How.CSS, using = "div.bootstrap-dialog-body")
    public WebElement randomQuestionDialog;
    @FindBy(how = How.ID, using = "closeChecking")
    public WebElement randomQuestionDialogCloseButton;
}


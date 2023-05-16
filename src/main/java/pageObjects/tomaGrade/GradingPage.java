package pageObjects.tomaGrade;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GradingPage {

    @FindBy(how = How.CSS, using = "button.gradingByStudentsButton")
    public WebElement gradingByStudentButton;
    @FindBy(how = How.CSS, using = "button.gradingByQuestionsButton")
    public WebElement gradingByQuestionButton;
    @FindBy(how = How.CSS, using = "button.tx-back-top")
    public WebElement backToStudentListButton;
    @FindBy(how = How.CSS, using = "div.checking-right-top div")
    public WebElement gradedCourseDetails;
    @FindBy(how = How.CSS, using = "button._finishAndCloseExam")
    public WebElement approveScoreButton;
    @FindBy(how = How.CSS, using = "div.bottomOperationsBar div.helpMainBtn")
    public WebElement previousExamArrowButton;
    @FindBy(how = How.ID, using = "nextExamArrow")
    public WebElement nextExamArrowButton;
    @FindBy(how = How.CSS, using = "div.currentParNumber")
    public WebElement examineDetails;
    @FindBy(how = How.CSS, using = "span.currentPageNumber")
    public WebElement currentPageNumber;
    @FindBy(how = How.ID, using = "addGradeInputElem")
    public WebElement gradeInputField;
    @FindBy(how = How.CSS, using = "span[data-selector='maxGradeHolder']")
    public WebElement maximumGradeForCurrentQuestion;
    @FindBy(how = How.ID, using = "gradeTextArea")
    public WebElement gradeCommentTextField;
    @FindBy(how = How.ID, using = "okBtn")
    public WebElement approveGradeButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'cancel')]")
    public WebElement cancelGradeButton;
    @FindBy(how = How.ID, using = "quationDetailsInput")
    public WebElement targetQuestionSelector;
    @FindBy(how = How.CSS, using = "img[ng-click='nextQuestion()']")
    public WebElement nextQuestionArrow;
    @FindBy(how = How.CSS, using = "img[ng-click='prevQuestion()']")
    public WebElement previousQuestionArrow;
    @FindBy(how = How.CSS, using = "div.bootstrap-dialog-message")
    public WebElement finishedGradingAllQuestionsAlert;
    @FindBy(how = How.CSS, using = "button[ng-click='$close(true)']")
    public WebElement finishedGradingAllQuestionsAlertCloseButton;
    @FindBy(how = How.CSS, using = "textarea[ng-model='stickynoteObj.stickynoteText']")
    public WebElement stickyNoteTextArea;
    @FindBy(how = How.CSS, using = "div.modal-content button.green-btn:nth-child(1)")
    public WebElement stickyNoteApproveButton;
    @FindBy(how = How.CSS, using = "span.currentPageNumber")
    public WebElement selectedStudentPosition;
    @FindBy(how = How.CSS, using = "div.currentParNumber")
    public WebElement selectedStudentID;
    @FindBy(how = How.CSS, using = "div.gradeDiv div.ng-binding")
    public WebElement plusOrMinusGradingRangeText;


    //---Lists---
    @FindBy(how = How.CSS, using = "div.tx-final-box-body span")
    public List<WebElement> examScore;
    @FindBy(how = How.CSS, using = "div[id='rightPartNav'] a i")
    public List<WebElement> gradingProcessActionLinks;
    @FindBy(how = How.CSS, using = "div[id='rightPartNav'] a span")
    public List<WebElement> gradingProcessActionLinksText;
    @FindBy(how = How.CSS, using = "div.canvas-container canvas.upper-canvas")
    public List<WebElement> canvasPagesList;
    @FindBy(how = How.CSS, using = "div[data-type='annotationContainer']")
    public List<WebElement> circleGradeAnnotationList;
    @FindBy(how = How.CSS, using = "div[data-annotation='gradeText']")
    public List<WebElement> gradeTextAnnotationList;
    @FindBy(how = How.CSS, using = "div[data-annotation='text'] span")
    public List<WebElement> textAnnotationList;
    @FindBy(how = How.CSS, using = "div[data-annotation='text']")
    public List<WebElement> textAnnotationBoxesList;
    @FindBy(how = How.CSS, using = "div.modal-content button.green-btn:nth-child(2)")
    public List<WebElement> stickyNoteCancelButton;
    @FindBy(how = How.CSS, using = "span.grade-icon-button")
    public List<WebElement> smallGIconsList;
    @FindBy(how = How.CSS, using = "div.grade-keyboard div button")
    public List<WebElement> calcButtonsList;
    @FindBy(how = How.CSS, using = "input[ng-if=' questionRow.IsCanceled != true']")
    public List<WebElement> questionGradeFieldList;
    @FindBy(how = How.CSS, using = "input[ng-if='questionRow.QNotAnswered']")
    public List<WebElement> notAnsweredQuestionGradeFieldList;
    @FindBy(how = How.CSS, using = "input[ng-model='subQuestionRow.QGrade']")
    public List<WebElement> subQuestionGradeFieldList;
    @FindBy(how = How.CSS, using = "input[ng-if='subQuestionRow.QNotAnswered']")
    public List<WebElement> notAnsweredSubQuestionGradeFieldList;
    @FindBy(how = How.CSS, using = "input[ng-model='secondeSubQuestionRow.QGrade']")
    public List<WebElement> secondSubQuestionGradeFieldList;
    @FindBy(how = How.CSS, using = "input[ng-if='secondeSubQuestionRow.QNotAnswered']")
    public List<WebElement> notAnsweredSecondSubQuestionGradeFieldList;
    @FindBy(how = How.CSS, using = "span.grade-icon-button img")
    public List<WebElement> rightSideGAnnotationIconsList;
    @FindBy(how = How.CSS, using = "div[ng-if='!questionRow.toDelete']")
    public List<WebElement> questionsRowList;
    @FindBy(how = How.CSS, using = "div[ng-if='!subQuestionRow.toDelete']")
    public List<WebElement> subQuestionsRowList;
    @FindBy(how = How.CSS, using = "div[ng-if='!secondeSubQuestionRow.toDelete']")
    public List<WebElement> secondSubQuestionsRowList;
    @FindBy(how = How.CSS, using = "span.QFullGrade")
    public List<WebElement> questionsMaxPossibleGradeList;
    @FindBy(how = How.CSS, using = "div.tx-comments-box div.commentsBankContainer a")
    public List<WebElement> savedCommentsRowList;
    @FindBy(how = How.CSS, using = "div.tx-comment-icons-end i")
    public List<WebElement> savedTextCommentsTrashIconList;
    @FindBy(how = How.CSS, using = "div[data-selector='commentHolder']")
    public List<WebElement> savedTextCommentsList;
    @FindBy(how = How.CSS, using = "input[ng-if='questionRow.QNotAnswered']")
    public List<WebElement> noAnswerQuestionCell;
    @FindBy(how = How.CSS, using = "input[ng-if='subQuestionRow.QNotAnswered']")
    public List<WebElement> noAnswerSubQuestionCell;
    @FindBy(how = How.CSS, using = "input[ng-if='secondeSubQuestionRow.QNotAnswered']")
    public List<WebElement> noAnswerSecondSubQuestionCell;


    //---Popup Alerts---
    @FindBy(how = How.CSS, using = "div.modal-body")
    public WebElement examSetupQuestionAlert;
    @FindBy(how = How.ID, using = "closeChecking")
    public WebElement closeExamSetupQuestionAlertButton;

}

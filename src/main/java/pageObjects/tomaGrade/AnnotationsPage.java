package pageObjects.tomaGrade;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AnnotationsPage {

    //General
    @FindBy(how = How.CSS, using = " div.scrolEditorBar")
    public List<WebElement> annotationToolbarSections;
    @FindBy(how = How.CSS, using = "div[id='toolbar_others_delete'] img")
    public WebElement deleteAnnotationButton;


    //Plus
    @FindBy(how = How.ID, using = "toolbar_grades_plus")
    public WebElement plusGradeAnnotator;
    @FindBy(how = How.ID, using = "div[id='toolbar_grades_plus'] span")
    public WebElement plusGradeDropdownButton;
    @FindBy(how = How.XPATH, using = "//a[contains(@ng-mouseup,'Plus')]")
    public List<WebElement> plusGradeAnnotationList;


    //Minus
    @FindBy(how = How.ID, using = "toolbar_grades_minus")
    public WebElement minusGradeAnnotator;
    @FindBy(how = How.ID, using = "div[id='toolbar_grades_minus'] span")
    public WebElement minusGradeDropdownButton;
    @FindBy(how = How.XPATH, using = "//a[contains(@ng-mouseup,'Minus')]")
    public List<WebElement> minusGradeAnnotationList;


    //G
    @FindBy(how = How.ID, using = "toolbar_grades_final")
    public WebElement gAnnotatorContainer;
    @FindBy(how = How.ID, using = "annotationBarButton_hasGradeArray")
    public WebElement gAnnotatorDropdownButton;
    @FindBy(how = How.CSS, using = "img[alt='setQuestionGrade']")
    public WebElement gAnnotatorMainButton;
    @FindBy(how = How.ID, using = "choose0")
    public WebElement innerGAnnotatorButton;


    //V
    @FindBy(how = How.ID, using = "toolbar_annotations_v_question")
    public WebElement vAnnotatorContainer;
    @FindBy(how = How.ID, using = "annotationBarButton_full_points")
    public WebElement vAnnotatorDropdownButton;
    @FindBy(how = How.CSS, using = "div[id=toolbar_annotations_v_question] img")
    public WebElement vAnnotatorMainButton;
    @FindBy(how = How.ID, using = "choose1")
    public WebElement innerVAnnotatorButton;


    //Crossed v
    @FindBy(how = How.ID, using = "toolbar_annotations_vx_question")
    public WebElement crossedVAnnotatorContainer;
    @FindBy(how = How.ID, using = "annotationBarButton_half_points")
    public WebElement crossedVAnnotatorDropdownButton;
    @FindBy(how = How.CSS, using = "div[id=toolbar_annotations_vx_question] img")
    public WebElement crossedVAnnotatorMainButton;
    @FindBy(how = How.ID, using = "choose3")
    public WebElement innerCrossedVAnnotatorButton;


    //X
    @FindBy(how = How.ID, using = "toolbar_annotations_x_question")
    public WebElement xAnnotatorContainer;
    @FindBy(how = How.ID, using = "annotationBarButton_no_points")
    public WebElement xAnnotatorDropdownButton;
    @FindBy(how = How.CSS, using = "div[id=toolbar_annotations_x_question] img")
    public WebElement xAnnotatorMainButton;
    @FindBy(how = How.ID, using = "choose2")
    public WebElement innerXAnnotatorButton;


    //No answer
    //todo replace with ID after Daniel replaces the duplicated ID
    @FindBy(how = How.XPATH, using = "toolbar_annotations_x_question")
    public WebElement noAnswerAnnotatorContainer;
    @FindBy(how = How.ID, using = "annotationBarButton_no_answer")
    public WebElement noAnswerAnnotatorDropdownButton;
    //TODO - Replace with ID selector after Daniel replaces duplicated ID
    @FindBy(how = How.XPATH, using = "(//div[@id='toolbar_annotations_x_question'])[2]")
    public WebElement noAnswerAnnotatorMainButton;

    @FindBy(how = How.ID, using = "choose4")
    public WebElement innerNoAnswerAnnotatorButton;

    //Rotate
    @FindBy(how = How.ID, using = "toolbar_rotate_left")
    public WebElement rotateLeftButton;
    @FindBy(how = How.ID, using = "toolbar_rotate_right")
    public WebElement rotateRightButton;
    @FindBy(how = How.ID, using = "toolbar_rotate_opposite")
    public WebElement upsideDownButton;

    //Text comments
    @FindBy(how = How.CSS, using = "div[id='toolbar_annotations_comment'] img")
    public WebElement textCommentButton;
    @FindBy(how = How.CSS, using = "div[id='toolbar_annotations_stickynote']")
    public WebElement stickyCommentButton;

}

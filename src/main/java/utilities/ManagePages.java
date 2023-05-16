package utilities;


import org.openqa.selenium.support.PageFactory;
import pageObjects.tomaGrade.TomaGradeLoginPage;
import pageObjects.tomaGrade.*;

public class ManagePages extends Base {
    public static void initWeb() {
        GradeLoginPage = PageFactory.initElements(driver, TomaGradeLoginPage.class);
        GradeArrayPage = PageFactory.initElements(driver, GradeArrayPage.class);
        HomePage = PageFactory.initElements(driver, HomePage.class);
        GradingPage = PageFactory.initElements(driver, GradingPage.class);
        AnnotationPage = PageFactory.initElements(driver, AnnotationsPage.class);
    }
}

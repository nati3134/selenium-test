package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listeners extends CommonOperations implements ITestListener {
    public void onFinish(ITestContext test) {
        System.out.println("---Tests Execution Ended---");
    }

    public void onStart(ITestContext test) {
        System.out.println("---Tests Execution Started---");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestFailure(ITestResult test) {
        System.out.println("---Test: " + test.getName() + " failed---");
            saveScreenshot();
    }
    public void onTestSkipped(ITestResult test) {
        System.out.println("---Test: " + test.getName() + " was skipped---");
    }

    public void onTestStart(ITestResult test) {
        System.out.println("---Test: " + test.getName() + " is starting---");
    }

    public void onTestSuccess(ITestResult test) {
        System.out.println("---Test: " + test.getName() + " passed---");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
       return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}



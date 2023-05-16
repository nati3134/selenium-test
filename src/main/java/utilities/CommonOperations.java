package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

import static java.awt.GraphicsEnvironment.isHeadless;

public class CommonOperations extends Base {

    public static void initBrowser(String browserType, String url, int timeout, int pageLoadTimeout) {
        switch (browserType) {
            case ("chrome"):
                driver = initChromeDriver();
                break;
            case ("firefox"):
                driver = initIFFDriver();
                break;
            case ("internetExplorer"):
                driver = initIEDriver();
                break;
            case ("edge"):
                driver = initEdgeDriver();
                break;
            default:
                throw new RuntimeException(("Invalid browser name stated"));
        }
        driver.manage().window().maximize();
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds((timeout)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        action = new Actions(driver);
        headlessModeConfiguration();
    }

    public static WebDriver initChromeDriver() {
        return new ChromeDriver();
    }

    public static WebDriver initIFFDriver() {
        return new FirefoxDriver();
    }

    public static WebDriver initIEDriver() {
        return new InternetExplorerDriver();
    }

    public static WebDriver initEdgeDriver() {
        return new EdgeDriver();
    }


    public static void headlessModeConfiguration() {
        boolean headlessModeStatus = isHeadless();
        if (!headlessModeStatus)
            System.out.println("Running on normal mode");
        else {
            System.out.println("Running on headless mode");
            System.setProperty("java.awt.headless", "true");
            Toolkit tk = Toolkit.getDefaultToolkit();
            // GraphicsEnvironment ge =
            // GraphicsEnvironment.getLocalGraphicsEnvironment();
            boolean headless_check = isHeadless();
            System.out.println(headless_check);
        }
    }
}

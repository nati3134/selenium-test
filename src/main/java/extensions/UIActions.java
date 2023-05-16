package extensions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOperations;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UIActions extends CommonOperations {

    //---Ghost elements actions - with JS executor---
    @Step("Click on before pseudo element")
    public static void clickPseudoBefore(WebElement pseudoElem) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", pseudoElem);
        wait.until(ExpectedConditions.elementToBeClickable(pseudoElem));
        pseudoElem.click();
    }

    @Step("Click on after pseudo element")
    public static void clickPseudoAfter(WebElement PseudoElem, String platformName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", PseudoElem);
        js.executeScript("arguments[0].click();", PseudoElem);
        if (platformName.equalsIgnoreCase("web")) {
            wait.until(ExpectedConditions.elementToBeClickable(PseudoElem));
        }
        PseudoElem.click();
    }

    //---Scroll actions---
    @Step("Scroll down by given number of pixels")
    public static void scrollDownByNumOfPixels(int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String scrollLimit = "window.scrollBy(0," + pixel + ")";
        js.executeScript(scrollLimit);
    }

    @Step("Scroll until element is Visible")
    public static void scrollUntilElementIsVisible(WebElement elem) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", elem);
    }

    @Step("Scroll down until the end of the page")
    public static void
    scrollDownToEndOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //---Select actions---
    @Step("Select from drop-down menu by visible text")
    public static void selectByVisibleText(WebElement elem, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        Select MyValue = new Select(elem);
        MyValue.selectByVisibleText(value);
    }

    @Step("Select From drop-down menu by value")
    public static void selectByValue(WebElement elem, String value) {
        Select MyValue = new Select(elem);
        MyValue.selectByValue(value);
    }

    @Step("Select from drop-down menu by index")
    public static void selectByIndex(WebElement elem, int index) {
        action.moveToElement(elem).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        Select MyValue = new Select(elem);
        MyValue.selectByIndex(index);
    }

    @Step("Get and return all available drop-down options")
    public static List<WebElement> getAllOptionsInDropDown(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        Select MyValue = new Select(elem);
        List<WebElement> listItems = MyValue.getOptions();
        for (WebElement index : listItems) {
            System.out.println(index.getText());
        }
        System.out.println(listItems.size());
        return listItems;
    }

    //---Action class is used---

    @Step("Click on element using Action class")
    public static void clickElementUsingActionsClass(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        action.moveToElement(elem).click().build().perform();
    }
    @Step("Select multiple by index")
    public static void selectMultipleByIndex(List<WebElement> list, int index1, int index2) {
        wait.until(ExpectedConditions.elementToBeClickable(list.get(index1)));
        wait.until(ExpectedConditions.elementToBeClickable(list.get(index2)));
        action.clickAndHold(list.get(index1)).clickAndHold(list.get(index2)).build().perform();
    }

    @Step("Drag & drop element")
    public static void dragAndDropElement(WebElement draggable, WebElement droppable) {
        wait.until(ExpectedConditions.visibilityOf(draggable));
        action.dragAndDrop(draggable, droppable).build().perform();
    }

    @Step("Double click on element")
    public static void doubleClickOnElement(WebElement dClick) {
        wait.until(ExpectedConditions.elementToBeClickable(dClick));
        action.doubleClick(dClick).build().perform();
    }

    @Step("Right click on element")
    public static void rightClickOnElement(WebElement rightClick) {
        wait.until(ExpectedConditions.elementToBeClickable(rightClick));
        action.contextClick(rightClick).build().perform();
    }

    @Step("ArrowDown")
    public static void arrowDown(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        action.click(elem).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
    }

    @Step("ArrowUp")
    public static void arrowUp(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        action.click(elem).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
    }

    @Step("Click Enter")
    public static void clickEnter(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        action.click(elem).sendKeys(Keys.RETURN).build().perform();
    }

    //simulates CNTRL + LEFT Click
    @Step("Select multiple items")
    public static void selectMultipleItems(List<WebElement> list) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < list.size() - 1; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).click(list.get(i)).click(list.get(i + 1)).keyUp(Keys.LEFT_CONTROL).build().perform();
        }
    }

    @Step("Left click kept press")
    public static void leftClickAndHold(WebElement elem) {
        action.moveToElement(elem);
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        action.clickAndHold(elem).build().perform();
    }

    @Step("Move to element")
    public static void moveToElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        action.moveToElement(elem).build().perform();
    }

    //---General actions for a single element---
    @Step("Click on element")
    public static void click(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("Mouse hover on element")
    public static void mouseHoverOnElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        action.moveToElement(elem).build().perform();
    }

    @Step("Clear and update text in text field")
    public static void setText(WebElement elem, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.clear();
        elem.sendKeys(value);
    }

    @Step("Clear text in text field")
    public static void clearText(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.clear();
    }

    @Step("Update text in text field")
    public static void updateText(WebElement elem, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.sendKeys(value);
    }

    @Step("Get an element attribute value")
    public static String getElementAttributeContent(WebElement elem, String attributeName) {
        String attributeText = elem.getAttribute(attributeName);
        return attributeText;
    }

    //Switches focus to a new opened window if exist
    //To switch back to original window just use "driver.switchTo().window(currentWinHandle);"
    @Step("Switch to another open window")
    public static String getWindowHandle() {
        String currentWinHandle = driver.getWindowHandle();
        System.out.println("Original window handle is " + currentWinHandle);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        String newWindowHandle = driver.getWindowHandle();
        if (!newWindowHandle.equalsIgnoreCase(currentWinHandle))
            System.out.println("switched to new window handle named " + newWindowHandle);
        else System.out.println("The focus remained on the original window");
        return newWindowHandle;
    }

    @Step("Get current url")
    public static String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    //Hasn't been tested yet
    @Step("Select entire text")
    public static void selectEntireText(WebElement elem) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        elem.sendKeys(selectAll);
    }

}
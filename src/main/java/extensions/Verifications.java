package extensions;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonOperations;
import io.qameta.allure.Step;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class Verifications extends CommonOperations {

    //---Verifications for lists ---
    @Step("Verify text correctness of each web element in a list")
    public static void textInEachListIndex(List<WebElement> items, String[] expectedValues) {
        for (int i = 0; i < items.size(); i++) {
          assertEquals((items.get(i).getText()), expectedValues[i]);
        }
    }

    @Step("Verify attribute value of each web element in a list")
    public static void attributeValueInEachListIndex(List<WebElement> items, String attribute, String[] expectedValues) {
        for (int i = 0; i < items.size(); i++) {
          assertEquals((items.get(i).getAttribute(attribute)), expectedValues[i]);
        }
    }

    @Step("Verify all elements in a list are displayed")
    public static void visibilityOfAllElementsOfAList(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
          assertTrue(list.get(i).isDisplayed());
        }
    }

    @Step("Verify all elements in a list are NOT displayed")
    public static void nonVisibilityOfAllElementsOfAList(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            assertFalse(list.get(i).isDisplayed());
        }
    }

    @Step("Verify all element in a list are enabled")
    public static void availabilityOfAllElementsInAList(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            wait.until(ExpectedConditions.visibilityOf(list.get(i)));
            assertTrue(list.get(i).isEnabled());
        }
    }

    @Step("Verify all element in a list are enabled")
    public static void nonAvailabilityOfAllElementsInAList(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            wait.until(ExpectedConditions.visibilityOf(list.get(i)));
            assertFalse(list.get(i).isEnabled());
        }
    }

    @Step("Verify none of the elements in a list displays text")
    public static void verifyElementsAreNotEmpty(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
           assertFalse((list.get(i).getAttribute("value").isEmpty()));
        }
    }

    @Step("Verify each element in a list does NOT display text")
    public static void verifyElementsAreEmpty(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            assertTrue((list.get(i).getAttribute("value").isEmpty()));
        }
    }

    @Step("Verify correct list size")
    public static void numberOfElementsInAList(List<WebElement> elements, int expectedListSize) {
        wait.until(ExpectedConditions.visibilityOf(elements.get(elements.size() - 1)));
        assertEquals(elements.size(), expectedListSize);
    }

    //In case all list indexes have the same text  - after sorting
    @Step("Verify text correctness of each web element in a list")
    public static void repeatableTextInEachListIndex(List<String> items, String repeatableText) {
        for (int i = 0; i < items.size(); i++) {
            assertTrue(items.get(i).equalsIgnoreCase(repeatableText));
        }
    }


    //--- Verifications for JSON objects ---
    @Step("Verify text correctness of each element in a list")
    public static void textInEachElementIndex(Elements items, String[] expectedValues) {
        for (int i = 0; i < items.size(); i++) {
            assertEquals((items.get(i).text()), expectedValues[i]);
        }
    }

    @Step("Verify JSON Objects list size")
    public static void verifyStringListSize(List<String> list, int expectedSize) {
        {
            System.out.println("The actual size of the list is " + list.size());
            assertTrue((list.size() == expectedSize));
        }
    }

    @Step("Verify Elements list size")
    public static void verifyElementsListSize(Elements list, int expectedSize) {
        {
            System.out.println("The actual size of the list is " + list.size());
            assertTrue((list.size() == expectedSize));
        }
    }

    @Step("Verify Elements list size")
    public static void verifyWebElementsListSize(List<WebElement>  list, int expectedSize) {
        {
            System.out.println("The actual size of the list is " + list.size());
            assertTrue((list.size() == expectedSize));
        }
    }

    //----Verifications for a single element----
    @Step("Verify the text of an element")
    public static void textInElement(WebElement elem, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(), expectedValue);
    }

    @Step("Verify a given text does NOT equal the text of a given element")
    public static void textNotInElement(WebElement elem, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertNotEquals(elem.getText(), expectedValue);
    }

    @Step("Compare actual to expected string value")
    public static void compareStringValues(String actual, String expectedValue) {
        assertEquals(actual, expectedValue);
    }

    @Step("Compare actual to expected int value")
    public static void compareIntValues(int actual, int expectedValue) {
        assertEquals(actual, expectedValue);
    }

    @Step("Verify multiple lines text of an element ")
    public static void textInElementMultipleLines(WebElement elem, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(elem));
       assertTrue(elem.getText().contains(expectedValue));
    }

    @Step("Verify text of an Input tag element")
    public static void textInElementForInputTags(WebElement elem, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getAttribute("value"), expectedValue);
    }

    @Step("Verify tag name of an element")
    public static void tagNameOfElement(WebElement elem, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getTagName(), expectedValue);
    }

    @Step("Verify an element is displayed")
    public static void visibilityOfElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertTrue(elem.isDisplayed());
    }

    @Step("Verify an element is Not displayed")
    public static void nonVisibilityOfElement(WebElement elem) {
        assertFalse(elem.isDisplayed());
    }

    @Step("Verify an element is selected")
    public static void selectedElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertTrue(elem.isSelected());
    }

    @Step("Verify an element is NOT Selected")
    public static void notSelectedElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertFalse(elem.isSelected());
    }

    @Step("Verify an element is enabled")
    public static void availabilityOfElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertTrue(elem.isEnabled());
    }

    @Step("Verify an element is Not enabled")
    public static void nonAvailabilityOfElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertFalse(elem.isEnabled());
    }

    @Step("Get and Verify element color")
    public static void getAndVerifyColor(WebElement FindMyColor, String ExpectedRGBAValue) {
        String itemColor = FindMyColor.getCssValue("color");
        assertEquals(itemColor, ExpectedRGBAValue);
    }

    @Step("Get and verify element background color")
    public static void getAndVerifyBackgroundColor(WebElement findMyColor, String expectedRGBAValue) {
        String itemBackgroundColor = findMyColor.getCssValue("background-color");
        assertEquals(itemBackgroundColor, expectedRGBAValue);
    }

    @Step("Get and verify an element attribute value")
    public static void verifyElementAttributeContent(WebElement elem, String attributeName, String attributeValue) {
        assertEquals((elem.getAttribute(attributeName)), attributeValue);
    }

    @Step("Verify element attribute contains a specific value")
    public static void verifyElementAttributeContainsAGivenValue(WebElement elem, String attributeName, String attributeValue) {
        assertTrue((elem.getAttribute(attributeName)).contains(attributeValue));
    }
    @Step("Verify element attribute does NOT contains a specific value")
    public static void verifyElementAttributeDoesNotContainsAGivenValue(WebElement elem, String attributeName, String attributeValue) {
        assertFalse((elem.getAttribute(attributeName)).contains(attributeValue));
    }


    @Step("Verify an element's text contains a specific value")
    public static void verifyATextIsIncluded(WebElement elem, String expectedValue) {
        assertTrue(elem.getText().contains(expectedValue));
    }

    @Step("Verify if a String contains a specific text")
    public static void verifyATextIsIncludedInAString(String str, String expectedValue) {
        assertTrue(str.contains(expectedValue));
    }
    @Step("Verify element does NOT display text")
    public static void verifyElementIsEmpty(WebElement elem) {
        assertTrue((elem.getAttribute("value").isEmpty()));
    }

    @Step("Verify element does display text")
    public static void verifyElementIsNotEmpty(WebElement elem) {
        assertFalse((elem.getAttribute("value").isEmpty()));
    }

    @Step("Verify a String does NOT contain a specific text")
    public static void verifyATextIsNotIncludedInAString(String str, String expectedValue) {
        assertFalse(str.contains(expectedValue));
    }

    //---Excel related verifications---
    @Step("Verify Text in a specific cell of an Excel sheet")
    public static void textInSpecificExcelCell(XSSFWorkbook relevantExcel, int sheetIndex, int rowIndex, int cellIndex, String expectedValue) {
        System.out.println(relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getCell(cellIndex).toString());
        assertEquals(relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getCell(cellIndex).toString(), expectedValue);
    }

    @Step("Verify the number of columns of an Excel sheet")
    public static void numOfColumns(XSSFWorkbook relevantExcel, int sheetIndex, int rowIndex, int columns) {
        System.out.println("The numbers of columns in the selected Excel sheet is" + " " + relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum());
        assertEquals(relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum(), columns);
    }

    @Step("Verify the number of rows of an Excel sheet")
    public static void numOfRows(XSSFWorkbook relevantExcel, int sheetIndex, int rows) {
        System.out.println("The numbers of rows in the selected Excel sheet is" + " " + relevantExcel.getSheetAt(sheetIndex).getLastRowNum());
        assertEquals(relevantExcel.getSheetAt(sheetIndex).getLastRowNum(), rows);
    }

    @Step("Verify Text of each cell in a specific row of an Excel sheet")
    public static String[] textInSpecificExcelRow(XSSFWorkbook relevantExcel, int sheetIndex, int rowIndex, String[] expectedValues) {
        String[] actualRowValues = new String[relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum()];
        for (int c = 0; c < relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum(); c++) {
            actualRowValues[c] = relevantExcel.getSheetAt(sheetIndex).getRow(rowIndex).getCell(c).toString();
            System.out.println("The cell values of the selected row are:" + " " + actualRowValues[c] + "," + " ");
        }
        assertEquals(actualRowValues, expectedValues);
        return actualRowValues;
    }

}
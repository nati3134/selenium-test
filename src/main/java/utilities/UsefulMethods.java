package utilities;

import io.qameta.allure.Step;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class UsefulMethods {
    public static void uploadFileWithRobot(String imagePath) {
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static String getData(String Configuration) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/DataConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }

        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(Configuration).item(0).getTextContent();
    }

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static LocalDateTime getPCDateTime(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current PC time and date is " + dtf.format(now));
        return now;
    }

    public static String calculateTimeDifference(LocalDateTime baseTime, LocalDateTime newTime) {
        String timeDifferenceInMilliseconds = String.valueOf(ChronoUnit.MILLIS.between(baseTime, newTime));
        System.out.println("The time difference in milliseconds is " + timeDifferenceInMilliseconds);
        return timeDifferenceInMilliseconds;
    }

    //Excel related actions
    @Step("Read and print Excel sheet")
    public static XSSFWorkbook readExcel(String excelFilePath, int sheetIndex) throws IOException {
        FileInputStream InputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook Workbook = new XSSFWorkbook(InputStream);
        XSSFSheet Sheet = Workbook.getSheetAt(sheetIndex);
        int Rows = Sheet.getLastRowNum();
        int Columns = Sheet.getRow(1).getLastCellNum();

        for (int r = 0; r <= Rows; r++) {
            XSSFRow Row = Sheet.getRow(r);
            for (int c = 0; c < Columns; c++) {
                XSSFCell Cell = Row.getCell(c);
                switch (Cell.getCellType()) {
                    case STRING:
                        System.out.print(Cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(Cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(Cell.getBooleanCellValue());
                        break;
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
        return Workbook;
    }

    @Step("Read Excel sheet using iterator")
    public static XSSFWorkbook readExcelIterator(String excelFilePath, int sheetIndex) throws IOException {
        FileInputStream InputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook Workbook = new XSSFWorkbook(InputStream);
        XSSFSheet Sheet = Workbook.getSheetAt(sheetIndex);
        Iterator iterator = Sheet.iterator();
        while (iterator.hasNext()) {
            XSSFRow row = (XSSFRow) iterator.next();
            Iterator CellIterator = row.cellIterator();
            while (CellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) CellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        cell.getBooleanCellValue();
                        break;
                }
                // System.out.println(" | ");
            }
            //System.out.println();
        }
        return Workbook;
    }

    //Word related actions
    @Step("Read and Print Entire Word Document")
    public static String readEntireWordDocument(String wordDocName) throws IOException {
        // try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(wordDocName))))
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(wordDocName))) {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            System.out.println(docText);
            // find number of words in the document
            long count = Arrays.stream(docText.split("\\s+")).count();
            System.out.println("Total words: " + count);
            return docText;
        }
    }

    @Step("Read Print and Return Word Document's Header")
    public static String[] getWordDocumentHeader(String path) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
        java.util.List<XWPFHeader> HeaderList = doc.getHeaderList();
        String[] arr = new String[HeaderList.size()];
        for (int i = 0; i < HeaderList.size(); i++) {
            arr[i] = (HeaderList.get(i).getText());
            System.out.println("Header's Text is: " + HeaderList.get(i).getText());
        }
        return arr;
    }

    @Step("Read Print and Return Word Document's Footer")
    public static String[] getWordDocumentFooter(String path) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
        java.util.List<XWPFFooter> FooterList = doc.getFooterList();
        String[] arr = new String[FooterList.size()];
        for (int i = 0; i < FooterList.size(); i++) {
            arr[i] = (FooterList.get(i).getText());
            System.out.println("Footer's Text is: " + FooterList.get(i).getText());
        }
        return arr;
    }

    @Step("Read print and Return Word Document's Paragraphs and Tables")
    public static String[] getWordDocumentElements(String path) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
        java.util.List<IBodyElement> bodyElements = doc.getBodyElements();
        String[] elementsText = new String[bodyElements.size()];

        for (int i = 0; i < bodyElements.size(); i++) {
            System.out.println("Body part type is: " + bodyElements.get(i).getClass());
            if (bodyElements.get(i) instanceof XWPFParagraph) {
                XWPFParagraph Paragraph = (XWPFParagraph) bodyElements.get(i);
                System.out.println(Paragraph.getText());
                elementsText[i] = Paragraph.getText();
            } else if (bodyElements.get(i) instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) bodyElements.get(i);
                System.out.println("table text " + table.getText());
                elementsText[i] = table.getText();
                java.util.List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    System.out.println("New row with cells: " + row.getTableCells().size());
                    for (XWPFTableCell tableCell : tableCells)
                        System.out.println(tableCell.getText());
                }
            }
        }
        return elementsText;
    }

}

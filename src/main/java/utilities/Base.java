package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.tomaGrade.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Base {

    //Web - general
    public static WebDriver driver;
    public static Actions action;
    public static WebDriverWait wait;
    public boolean runSingleFile = false;

    //Web - Page objects
    public static TomaGradeLoginPage GradeLoginPage;
    public static HomePage HomePage;
    public static GradeArrayPage GradeArrayPage;
    public static GradingPage GradingPage;
    public static AnnotationsPage AnnotationPage;

//API
    public static RequestSpecification httpRequest;
    public static Response response;
    public static JSONObject RequestParameters = new JSONObject();
    public static JsonPath jp;
    public static Document doc;

//Data Base
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
}

package extensions;

import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import utilities.CommonOperations;

import java.io.IOException;

public class APIActions extends CommonOperations {

    @Step("Get a specific note's property from server")
    public static String getSpecificRecordProperty(String resources, String recordProperty) {
        response = httpRequest.get(resources);
        jp = response.jsonPath();
        System.out.println(response.getStatusCode());
        jp.prettyPrint();
        return jp.get(recordProperty).toString();
    }

    @Step("Post data to server")
    public static void post(JSONObject parameters) {
        httpRequest.header("Content-Type", "application/json; charset=UTF-8");
        httpRequest.body(parameters.toJSONString());
    }
    @Step("Update data in server")
    public static void put(JSONObject parameters) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(parameters.toJSONString());
    }
    @Step("Delete data from server")
    public static int delete(String paramValues) {
        response = httpRequest.delete(paramValues);
        response.prettyPrint();
        System.out.println("Status code is " + response.getStatusCode());
        return response.getStatusCode();
    }
    @Step("Verify site title")
    public static void verifySiteTitle(String apiURL, String expectedSiteTitle) throws IOException {
        doc = Jsoup.connect(apiURL).get();
        System.out.println("Title is " + doc.title());
        Verifications.compareStringValues(doc.title(), expectedSiteTitle);
    }
    @Step("Verify list items text")
    public static void verifyListItemsText(String apiURL, String cssQuery, String[] expectedItemsText) throws IOException {
        doc = Jsoup.connect(apiURL).get();
        Elements values = doc.select(cssQuery);
        Verifications.textInEachElementIndex(values, expectedItemsText);
    }
}
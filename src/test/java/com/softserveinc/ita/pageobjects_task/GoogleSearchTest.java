package com.softserveinc.ita.pageobjects_task;

import com.softserveinc.ita.vPetrat.pageObjects.GoogleHomePage;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoogleSearchTest extends TestRunner {
    private String request;

    @Test
    public void testGoogleSearchFirstResultLinkText() {
        String searchResultLinkText = new GoogleHomePage().openHomePage().searchForTheSpecifiedRequest(request).getTextOfFirstLinkInSearchResults();
        Assert.assertTrue(searchResultLinkText.contains(request));
    }

    @BeforeClass
    public void setupSearchRequestFromOuterResource() {
        try(InputStream inputStream = new FileInputStream("src/main/java/com/softserveinc/ita/vPetrat/resources/googleSearchRequest.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            request = properties.getProperty("searchRequestKey");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}

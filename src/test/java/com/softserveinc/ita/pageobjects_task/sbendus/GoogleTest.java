package com.softserveinc.ita.pageobjects_task.sbendus;

import com.softserveinc.ita.sbendus.pageobject.TestRunner;
import com.softserveinc.ita.sbendus.pageobject.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void startTest() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testGoogleSearch() {
        String googleSearchMessage = "Funny Kitten";
        String firstLink = googleHomePage
                .searchFor(googleSearchMessage)
                .getSearchResultLinkText();
        Assert.assertTrue(firstLink.contains(googleSearchMessage));
    }

    @Test
    public void testGoogleSearchLinks() {
        String googleSearchMessage = "Smartphone";
        List<String> resultLinks = googleHomePage
                .searchFor(googleSearchMessage)
                .getListOfSearchResultLinks();
        Assert.assertTrue(resultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}


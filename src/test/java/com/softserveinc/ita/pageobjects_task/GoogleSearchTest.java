package com.softserveinc.ita.pageobjects_task;

import com.softserveinc.ita.vPetrat.pageObjects.GoogleHomePage;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleSearchTest extends TestRunner {
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomepage() {
        googleHomePage = new GoogleHomePage()
                .openHomePage();
    }

    @Test
    public void testGoogleSearchFirstResultLinkText() {
        String firstSearchResultLink = googleHomePage
                .searchFor("Funny Kitties")
                .getSearchResultLinkForIndex(0);
        Assert.assertTrue(firstSearchResultLink.contains("Funny Kitties"));
    }

    @Test
    public void testGoogleSearchAllResultLinkText() {
        List<String> searchResultLinks = googleHomePage
                .searchFor("Smartphone")
                .getListOfSearchResultLinks();
        Assert.assertTrue(searchResultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

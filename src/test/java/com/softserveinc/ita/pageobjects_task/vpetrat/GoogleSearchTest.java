package com.softserveinc.ita.pageobjects_task.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.vpetrat.GoogleHomePage;
import com.softserveinc.ita.vpetrat.GoogleSearchImagesPage;
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
                .searchFor("funny kitten")
                .getSearchResultLinkTextForIndex(0);
        Assert.assertTrue(firstSearchResultLink.toLowerCase().contains("funny kitten"));
    }

    @Test
    public void testGoogleSearchAllResultLink() {
        List<String> searchResultLinks = googleHomePage
                .searchFor("smartphone")
                .getListOfSearchResultLinks();
        Assert.assertTrue(searchResultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }

    @Test
    public void testGoogleSearchResultImagesText() {
        GoogleSearchImagesPage imageResultsPage = googleHomePage.searchFor("funny kitten").navigateToImagesPage();
        List<String> resultImagesText = imageResultsPage
                .getListOfSearchResultImagesText();
        String testParameter = "funny";
        Assert.assertTrue(resultImagesText.size() >= 10);
        Assert.assertTrue(resultImagesText.get(0).toLowerCase().contains(testParameter));
        Assert.assertTrue(resultImagesText.get(4).toLowerCase().contains(testParameter));
        imageResultsPage.navigateToHomePage();
        Assert.assertEquals("Google", TestRunner.getDriver().getTitle());
    }

    @Test
    public void testGoogleLuckySearch() {
        String searchResultUrl = googleHomePage.luckySearchFor("funny kitten").getPageUrl();
        Assert.assertTrue(searchResultUrl.contains("youtube"));
    }
}


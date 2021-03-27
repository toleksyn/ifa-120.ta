package com.softserveinc.ita.pageobjects_task.romankhr;

import com.softserveinc.ita.romankhr.GoogleHomePage;
import com.softserveinc.ita.romankhr.GoogleSearchResultImagesPage;
import com.softserveinc.ita.romankhr.TestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private String searchTerm = "funny kitten";
    private GoogleHomePage homePage;
    private GoogleSearchResultImagesPage imagePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        homePage = new GoogleHomePage().open();
    }

    @Test
    public void testGoogleSearch() {
        List<String> searchResultsTitles = homePage
                .searchFor(searchTerm)
                .getSearchResultLinks();

        Assert.assertTrue(searchResultsTitles.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testSmartphoneSearch() {
        List<String> searchResultsLinks = homePage
                .searchFor("smartphone")
                .getSearchResultsLinks();

        Assert.assertTrue(searchResultsLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }

    @Test
    public void testGoogleImagesSearch() {
        imagePage = homePage
                .searchFor(searchTerm).moveToImages();

        List<String> searchResultsImagesList = imagePage
                .getSearchResultImages();
        
        imagePage.moveToGoogleHomePage();

        Assert.assertTrue(searchResultsImagesList.size() > 9);

        Assert.assertTrue(searchResultsImagesList.get(0).toLowerCase().contains("funny") &&
                searchResultsImagesList.get(4).toLowerCase().contains("funny"));

        Assert.assertTrue(TestRunner.threadLocalDriver.get().getTitle().toLowerCase().contains("google"));
    }
}

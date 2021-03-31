package com.softserveinc.ita.pageobjects_task.romankhr;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.romankhr.GoogleHomePage;
import com.softserveinc.ita.romankhr.GoogleSearchResultImagesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private String searchTerm = "funny kitten";
    private GoogleHomePage homePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        homePage = new GoogleHomePage().open();
    }

    @Test
    public void testGoogleSearch() {
        List<String> searchResultsTitles = homePage
                .searchFor(searchTerm)
                .getSearchResultLinksTitles();

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
        String testWord = "funny";
        GoogleSearchResultImagesPage imagePage = homePage
                .searchFor(searchTerm).navigateToImagesPage();

        List<String> searchResultsImagesList = imagePage
                .getSearchResultImagesTitles();

        imagePage.navigateToGoogleHomePage();

        Assert.assertTrue(searchResultsImagesList.size() > 9);

        Assert.assertTrue(searchResultsImagesList.get(0).toLowerCase().contains(testWord));
        Assert.assertTrue(searchResultsImagesList.get(4).toLowerCase().contains(testWord));

        Assert.assertTrue(TestRunner.getDriver().getTitle().toLowerCase().contains("google"));
    }

    @Test
    public void testWebdriverSearch() {
        String testWord = "webdriver";
        List<String> searchResultsDescription = homePage
                .searchFor(testWord)
                .navigateToNumberedResultPage(10)
                .navigateToNumberedResultPage(14)
                .getSearchResultsDescription();

        Assert.assertTrue(searchResultsDescription.get(0).toLowerCase().contains(testWord));
    }

}

package com.softserveinc.ita.pageobjects_task_deprecated.romankhr;

import com.codeborne.selenide.WebDriverRunner;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.deprecated.romankhr.GoogleHomePage;
import com.softserveinc.ita.deprecated.romankhr.GoogleSearchResultImagesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private final String searchTerm = "funny kitten";
    private GoogleHomePage homePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        homePage = new GoogleHomePage().open();
    }

    @Test
    public void testGoogleSearch() {
        String searchResultTitle = homePage
                .searchFor(searchTerm)
                .getSearchResultLinkTitle();

        Assert.assertTrue(searchResultTitle.contains(searchTerm));
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

        Assert.assertTrue(WebDriverRunner.getWebDriver().getTitle().toLowerCase().contains("google"));
    }

    @Test
    public void testWebdriverSearch() {
        String testWord = "webdriver";
        String searchResultDescription = homePage
                .searchFor(testWord)
                .navigateToNumberedResultPage(10)
                .navigateToNumberedResultPage(14)
                .getSearchResultDescription(0);

        Assert.assertTrue(searchResultDescription.contains(testWord));
    }
}

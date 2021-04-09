package com.softserveinc.ita.pageobjects_task.nromanchuk;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.nromanchuk.BooksResultsPage;
import com.softserveinc.ita.nromanchuk.GoogleHomePage;
import com.softserveinc.ita.nromanchuk.ImagesResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage().openGoogleHomePage();
    }

    @Test
    public void testGoogleSearch() {
        String searchTerm = "funny kitten";
        String firstLinkText = googleHomePage
                .searchFor(searchTerm)
                .getLinkText(1)
                .toLowerCase();
        Assert.assertTrue(firstLinkText.contains(searchTerm));
    }

    @Test
    public void testWikiLinkInResults() {
        List<String> searchResultLinks = googleHomePage
                .searchFor("Smartphone")
                .getResultsLinks();
        Assert.assertTrue(searchResultLinks.stream().anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }

    @Test
    public void testGoogleImageSearch() {
        String testTermForAssert = "funny";
        ImagesResultsPage imagesResultsPage = googleHomePage
                .searchFor("funny kitten")
                .navigateToImageResultsPage();
        ElementsCollection listOfTextResults = imagesResultsPage.getListOfTextResults();
        Assert.assertTrue(listOfTextResults.size() > 9);
        Assert.assertTrue(listOfTextResults.get(0).getAttribute("title").toLowerCase().contains(testTermForAssert));
        Assert.assertTrue(listOfTextResults.get(4).getAttribute("title").toLowerCase().contains(testTermForAssert));
        imagesResultsPage.navigateToGoogleHomePage();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getTitle(), "Google", "Current Page isn't a Google homepage");
    }

    @Test
    public void testWebDriverSearch() {
        String searchTerm = "webdriver";
        BooksResultsPage booksResultsPage = googleHomePage.searchFor(searchTerm).navigateToBooksResultsPage();
        String resultLinkText = booksResultsPage.getResultLinkTextByIndex(9).toLowerCase();
        Assert.assertTrue(resultLinkText.contains(searchTerm));
    }
}

package com.softserveinc.ita.pageobjects_task.nromanchuk;

import com.softserveinc.ita.nromanchuk.GoogleHomePage;
import com.softserveinc.ita.nromanchuk.ImagesResultsPage;
import com.softserveinc.ita.nromanchuk.TestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage().open();
    }

    @Test
    public void testGoogleSearch() {
        String searchTerm = "funny kitten";
        String getFirstLinkText = googleHomePage
                .searchFor(searchTerm)
                .getLinkText(1);
        Assert.assertTrue(getFirstLinkText.contains(searchTerm));
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
        ImagesResultsPage imagesResultsPage = googleHomePage
                .searchFor("funny kitten")
                .switchToImageResults("//*[@id='hdtb-msb']/div[1]/div/div[2]/a");
        List<WebElement> listOfTextResults = imagesResultsPage.getListOfTextResults();
        Assert.assertTrue(listOfTextResults.size() > 9);
        Assert.assertTrue(listOfTextResults.get(0).getAttribute("title").toLowerCase().contains("funny") &&
                listOfTextResults.get(4).getAttribute("title").toLowerCase().contains("funny"));
        imagesResultsPage.goToHomePage();
        Assert.assertEquals(TestRunner.driver.getTitle(), "Google");
    }

}

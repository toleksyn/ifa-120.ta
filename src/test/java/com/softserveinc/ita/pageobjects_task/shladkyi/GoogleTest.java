package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.shladkyi.GoogleHomePage;
import com.softserveinc.ita.shladkyi.GoogleSearchImagesResultPage;
import com.softserveinc.ita.shladkyi.TestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testValidGoogleSearchFirstLink() {
        String searchTerm = "funny kitten";
        String firstLink = googleHomePage.searchFor(searchTerm).getSearchResultLinkText(0);
        Assert.assertTrue(firstLink.toLowerCase().contains(searchTerm));
    }

    @Test
    public void testValidGoogleSearchAnyLink() {
        List<String> allLinks = googleHomePage.searchFor("smartphone").getAllSearchResultLinks();
        Assert.assertTrue(allLinks.stream().anyMatch(link -> link.contains("wikipedia.org")));
    }

    @Test
    public void testValidGoogleImagesSearch() {
        GoogleSearchImagesResultPage googleImagesResultPage = googleHomePage.searchFor("funny kitten").openImagesPage();
        List<String> textOfImagesList = googleImagesResultPage.getListOfSearchResultImagesText();
        Assert.assertTrue(textOfImagesList.size() >= 10);
        String testText = "funny";
        Assert.assertTrue(textOfImagesList.get(0).toLowerCase().contains(testText));
        Assert.assertTrue(textOfImagesList.get(4).toLowerCase().contains(testText));
        googleImagesResultPage.openGoogleHomePage();
        Assert.assertTrue(TestRunner.driver.getCurrentUrl().contains("https://www.google.com/"));
    }

    @Test
    public void testLanguageChange() {
        GoogleHomePage homePage = googleHomePage.openSettings().changeGoogleLanguage("en");
        Assert.assertEquals(homePage.getTextOfSearchButton(), "Google Search");
        Assert.assertEquals(homePage.getTextOfLuckyButton(), "I'm Feeling Lucky");
        homePage.openSettings().changeGoogleLanguage("uk");
        Assert.assertEquals(homePage.getTextOfSearchButton(), "Пошук Google");
        Assert.assertEquals(homePage.getTextOfLuckyButton(), "Мені пощастить");
    }
}

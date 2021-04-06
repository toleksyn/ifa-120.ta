package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.shladkyi.GoogleHomePage;
import com.softserveinc.ita.shladkyi.GoogleSearchImagesResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.title;
import static java.util.Objects.requireNonNull;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage()
                .openHomePage();
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
        List<String> imagesListText = googleImagesResultPage.getSearchResultImagesTextList(10);
        Assert.assertTrue(imagesListText.size() >= 10);
        String testText = "funny";
        Assert.assertTrue(imagesListText.get(0).toLowerCase().contains(testText));
        Assert.assertTrue(imagesListText.get(4).toLowerCase().contains(testText));
        googleImagesResultPage.openGoogleHomePage();
        Assert.assertTrue(requireNonNull(title()).contains("Google"));
    }

    @Test
    public void testLanguageChange() {
        GoogleHomePage homePage = googleHomePage.openSettingsPage().changeGoogleLanguage("en");
        Assert.assertEquals(homePage.getSearchButtonText(), "Google Search");
        Assert.assertEquals(homePage.getLuckyButtonText(), "I'm Feeling Lucky");
        homePage.openSettingsPage().changeGoogleLanguage("uk");
        Assert.assertEquals(homePage.getSearchButtonText(), "Пошук Google");
        Assert.assertEquals(homePage.getLuckyButtonText(), "Мені пощастить");
    }
}

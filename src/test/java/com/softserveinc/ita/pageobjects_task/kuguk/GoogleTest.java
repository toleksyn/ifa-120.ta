package com.softserveinc.ita.pageobjects_task.kuguk;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.kuguk.GoogleHomePage;
import com.softserveinc.ita.kuguk.GoogleSearchResultsImagesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    private GoogleHomePage openGoogleHomePage;
    private List<String> searchResultsLinks;
    private String searchString;

    @BeforeMethod
    public void doSearch() {
        openGoogleHomePage = new GoogleHomePage().open();
        searchString = "funny kitten";
    }

    @Test
    public void testGoogleSearch() {
        searchResultsLinks = openGoogleHomePage.searchFor(searchString).getSearchResultsLinksText();
        assertTrue(searchResultsLinks.get(0).toLowerCase().contains("funny kitten"));
    }

    @Test
    public void testGoogleSearch_PresenceResultInList() {
        searchResultsLinks = openGoogleHomePage.searchFor("smartphone").getSearchResultsLinksList();
        assertTrue(searchResultsLinks.toString().toLowerCase().contains("wikipedia."));
    }

    @Test
    public void testGoogleSearchImages() {
        String testStringForAssert = "funny";
        GoogleSearchResultsImagesPage searchResultsImagesPage = openGoogleHomePage.searchFor(searchString)
                .navigateToImagePage();

        String[] searchResultsImages = searchResultsImagesPage.getSearchResultsImagesText();

        assertTrue(searchResultsImages.length > 9);

        assertTrue(searchResultsImages[0].toLowerCase().contains(testStringForAssert));
        assertTrue(searchResultsImages[4].toLowerCase().contains(testStringForAssert));

        searchResultsImagesPage.gotoGoogleHomePageByLogo();

        assertEquals(TestRunner.getDriver().getTitle(), "Google", "Current Page isn't a Google homepage");
    }
}

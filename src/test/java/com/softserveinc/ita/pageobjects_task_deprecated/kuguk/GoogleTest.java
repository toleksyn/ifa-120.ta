package com.softserveinc.ita.pageobjects_task_deprecated.kuguk;

import com.softserveinc.ita.deprecated.common.TestRunner;
import com.softserveinc.ita.deprecated.kuguk.GoogleHomePage;
import com.softserveinc.ita.deprecated.kuguk.GoogleSearchResultsImagesPage;
import com.softserveinc.ita.deprecated.kuguk.GoogleSearchSortResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.title;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    private GoogleHomePage openGoogleHomePage;
    private String searchString;

    @BeforeMethod
    public void doSearch() {
        openGoogleHomePage = new GoogleHomePage().open();
        searchString = "\"funny kitten\"";
    }

    @Test
    public void testGoogleSearch() {
        String searchResultsLinks = openGoogleHomePage
                .searchFor(searchString)
                .getSearchResultsLinksText(0);
        assertTrue(searchResultsLinks.toLowerCase().contains("funny kitten"));
    }

    @Test
    public void testGoogleSearch_PresenceResultInList() {
        List<String> searchResultsLinksList = openGoogleHomePage
                .searchFor("smartphone")
                .getSearchResultsLinksList();
        assertTrue(searchResultsLinksList.toString().toLowerCase().contains("wikipedia."));
    }

    @Test
    public void testGoogleSearchImages() {
        String testStringForAssert = "funny";

        GoogleSearchResultsImagesPage searchResultsImagesPage = openGoogleHomePage
                .searchFor(searchString)
                .navigateToImagePage();

        List<String> searchResultsImages = searchResultsImagesPage
                .getSearchResultsImagesText();

        assertTrue(searchResultsImages.size() > 9);

        assertTrue(searchResultsImages.get(0).toLowerCase().contains(testStringForAssert));
        assertTrue(searchResultsImages.get(4).toLowerCase().contains(testStringForAssert));

        searchResultsImagesPage.gotoGoogleHomePageByLogo();
        assertEquals(title(), "Google", "Current Page isn't a Google homepage");
    }

    @Test
    public void testGoogleTimeSortSearch() {
        String sortFilter = "Past hour";
        String testStringForAssert = "mins ago";

        openGoogleHomePage.openSettingsPage().changeGoogleLanguage("en");

        GoogleSearchSortResultsPage searchResultsTimeSort = openGoogleHomePage
                .searchFor("webdriver")
                .sortBy(sortFilter);
        assertTrue(searchResultsTimeSort.getSearchTimeSortResultsText(testStringForAssert, 0).toLowerCase()
                .contains(testStringForAssert));
    }
}

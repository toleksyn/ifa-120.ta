package com.softserveinc.ita.pageobjects_task_deprecated.ynamurovanyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.deprecated.ynamurovanyi.GoogleHomePage;
import com.softserveinc.ita.deprecated.ynamurovanyi.GoogleSearchImagesPage;
import com.softserveinc.ita.deprecated.ynamurovanyi.GoogleSearchResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testIfFirstLinkTextContains() {
        String searchTerm = "funny kitten";
        String searchResultsLinkText = googleHomePage
                .searchFor(searchTerm)
                .getSearchResultsLinkText(0);
        assertTrue(searchResultsLinkText.contains(searchTerm));
    }

    @Test
    public void testLinksForWikipedia() {
        List<String> searchResultsLinks = googleHomePage
                .searchFor("smartphone")
                .getSearchResultsLinks();
        assertTrue(searchResultsLinks
                .stream()
                .anyMatch(link -> link.contains("wikipedia.org")));
    }

    @Test
    public void testSearchImagesResults() {
        String expectedText = "funny";
        GoogleSearchImagesPage googleSearchImagesPage = googleHomePage
                .searchFor("funny kitten")
                .navigateToImagesResultsPage();
        List<String> searchResultsLinksText = googleSearchImagesPage
                .getSearchResultsLinksText();
        assertTrue(searchResultsLinksText.size() > 9);
        assertTrue(searchResultsLinksText.get(0).toLowerCase().contains(expectedText));
        assertTrue(searchResultsLinksText.get(4).toLowerCase().contains(expectedText));
        assertEquals(googleSearchImagesPage.navigateToHomePageByLogo().getTitle(), "Google");
    }

    @Test
    public void testGoogleSearchResultsPages() {
        GoogleSearchResultsPage fifthGoogleSearchResultsPage = googleHomePage
                .searchFor("funny kitten")
                .navigateToResultsPageNumber(5);
        assertEquals(fifthGoogleSearchResultsPage.getSearchResultsLinks().size(), 10);
        assertEquals(fifthGoogleSearchResultsPage.getSearchResultsPageNumber(), 5);
    }
}

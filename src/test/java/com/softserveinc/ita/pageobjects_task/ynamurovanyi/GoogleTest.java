package com.softserveinc.ita.pageobjects_task.ynamurovanyi;

import com.softserveinc.ita.ynamurovanyi.GoogleHomePage;
import com.softserveinc.ita.ynamurovanyi.GoogleSearchImagesPage;
import com.softserveinc.ita.ynamurovanyi.TestRunner;
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
        List<String> searchResultsLinksText = googleHomePage
                .searchFor(searchTerm)
                .getSearchResultsLinksText();
        assertTrue(searchResultsLinksText.get(0).contains(searchTerm));
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
        GoogleSearchImagesPage googleSearchImagesPage = googleHomePage
                .searchFor("funny kitten")
                .navigateToImagesResultsPage();
        List<String> searchResultsLinksText = googleSearchImagesPage
                .getSearchResultsLinksText();
        assertTrue(searchResultsLinksText.size() > 9);
        assertTrue(searchResultsLinksText.get(0).toLowerCase().contains("funny"));
        assertTrue(searchResultsLinksText.get(4).toLowerCase().contains("funny"));
        assertEquals(googleSearchImagesPage.navigateToHomePageByLogo().getTitle(), "Google");
    }
}

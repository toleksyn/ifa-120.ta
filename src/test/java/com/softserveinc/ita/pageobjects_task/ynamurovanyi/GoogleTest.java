package com.softserveinc.ita.pageobjects_task.ynamurovanyi;

import com.softserveinc.ita.ynamurovanyi.GoogleHomePage;
import com.softserveinc.ita.ynamurovanyi.GoogleSearchImagesPage;
import com.softserveinc.ita.ynamurovanyi.GoogleSearchResultsPage;
import com.softserveinc.ita.ynamurovanyi.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test(priority = -1)
    public void testIfFirstLinkTextContains() {
        List<String> searchResultsLinksText = new GoogleHomePage()
                .open()
                .searchFor("funny kitten")
                .getSearchResultsLinksText();
        assertTrue(searchResultsLinksText.get(0).contains("funny kitten"));
    }

    @Test
    public void testSearchImagesResults() {
        List<String> searchResultsLinksText = GoogleSearchResultsPage
                .getImages()
                .getSearchResultsLinksText();
        assertTrue((GoogleSearchImagesPage.goHomeByLogo().getTitle().equals("Google"))
                && searchResultsLinksText.size() > 9
                && searchResultsLinksText.get(0).toLowerCase().contains("funny")
                && searchResultsLinksText.get(4).toLowerCase().contains("funny"));
    }

    @Test(priority = 1)
    public void testLinksForWikipedia() {
        List<String> searchResultsLinks = new GoogleHomePage()
                .open()
                .searchFor("smartphone")
                .getSearchResultsLinks();
        assertTrue(searchResultsLinks
                .stream()
                .anyMatch(link -> link.contains("wikipedia.org")));
    }
}

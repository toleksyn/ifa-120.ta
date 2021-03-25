package com.softserveinc.ita.pageobjects_task.ynamurovanyi;

import com.softserveinc.ita.ynamurovanyi.GoogleHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void testIfFirstLinkTextContains() {
        List<String> searchResultsLinksText = new GoogleHomePage()
                .open()
                .searchFor("funny kitten")
                .getSearchResultsLinksText();
        assertTrue(searchResultsLinksText.get(0).contains("funny kitten"));
    }

    @Test
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

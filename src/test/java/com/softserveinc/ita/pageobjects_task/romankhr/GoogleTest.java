package com.softserveinc.ita.pageobjects_task.romankhr;

import com.softserveinc.ita.romankhr.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private String searchTerm = "funny kitten";
    private String searchTermLinkTest = "smartphone";

    @Test
    public void testGoogleSearch() {
        List<String> searchResultsLinks = new GoogleHomePage()
                .open()
                .searchFor(searchTerm)
                .getSearchResultLinks();

        Assert.assertTrue(searchResultsLinks.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testSmartphoneSearch() {
        List<String> searchResultsLinks = new GoogleHomePage()
                .open()
                .searchFor(searchTermLinkTest)
                .getSearchResultsLinks();

        Assert.assertTrue(searchResultsLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

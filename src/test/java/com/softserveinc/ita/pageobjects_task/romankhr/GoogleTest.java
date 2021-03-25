package com.softserveinc.ita.pageobjects_task.romankhr;

import com.softserveinc.ita.romankhr.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private String searchTerm = "funny kitten";

    @Test
    public void testGoogleSearch() {
        List<String> searchResultsTitles = new GoogleHomePage()
                .open()
                .searchFor(searchTerm)
                .getSearchResultLinks();

        Assert.assertTrue(searchResultsTitles.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testSmartphoneSearch() {
        List<String> searchResultsLinks = new GoogleHomePage()
                .open()
                .searchFor("smartphone")
                .getSearchResultsLinks();

        Assert.assertTrue(searchResultsLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

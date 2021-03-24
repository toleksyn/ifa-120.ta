package com.softserveinc.ita.pageobjects_task.romankhr1;

import com.softserveinc.ita.romankhr1.GoogleHomePage1;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {
    private String searchTerm = "funny kitten";
    private String searchTermSecondTest = "smartphone";

    @Test
    public void testGoogleSearch() {
        List<String> searchResultsLinks = new GoogleHomePage1()
                .open()
                .searchFor(searchTerm)
                .getSearchResultLinks();

        Assert.assertTrue(searchResultsLinks.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testSmartphoneSearch() {
        List<String> searchResultsLinks = new GoogleHomePage1()
                .open()
                .searchFor(searchTermSecondTest)
                .getSearchResultsLinks();

        Assert.assertTrue(searchResultsLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

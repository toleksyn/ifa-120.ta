package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.shladkyi.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void doSearch() {
        googleHomePage = new GoogleHomePage()
                .open();
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
}

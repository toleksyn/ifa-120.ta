package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.shladkyi.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    private String searchTerm;
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void doSearch() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testValidGoogleSearchFirstLink() {
        searchTerm = "funny kitten";
        Assert.assertTrue(googleHomePage
                .searchFor(searchTerm)
                .getFirstSearchResultLink()
                .toLowerCase()
                .contains("funny kitten"));
    }

    @Test
    public void testValidGoogleSearchAnyLink() {
        searchTerm = "smartphone";
        Assert.assertTrue(googleHomePage
                .searchFor(searchTerm)
                .getAllSearchResultLinks()
                .stream()
                .anyMatch(link -> link.contains("wikipedia.org")));
    }
}

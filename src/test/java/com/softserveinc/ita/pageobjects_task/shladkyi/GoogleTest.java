package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.shladkyi.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private List<String> searchOneResultLink;
    private String searchTerm;
    private GoogleHomePage googleHomePage;
    private List<String> searchAllResultLinks;

    @BeforeMethod
    public void doSearch() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testValidGoogleSearchFirstLink() {
        searchTerm = "funny kitten";
        searchOneResultLink = googleHomePage.searchFor(searchTerm).getOneSearchResultLink(0);
        Assert.assertTrue(searchOneResultLink.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testInValidGoogleSearchFirstLink() {
        searchTerm = "funny kitten";
        searchOneResultLink = googleHomePage.searchFor(searchTerm).getOneSearchResultLink(0);
        Assert.assertFalse(searchOneResultLink.contains("random"));
    }

    @Test
    public void testValidGoogleSearchAnyLink() {
        searchTerm = "smartphone";
        searchAllResultLinks = googleHomePage.searchFor(searchTerm).getAllSearchResultLinks();
        Assert.assertTrue(searchAllResultLinks.stream().anyMatch(link -> link.contains("wikipedia.org")));
    }

    @Test
    public void testInValidGoogleSearchAnyLink() {
        searchTerm = "smartphone";
        searchAllResultLinks = googleHomePage.searchFor(searchTerm).getAllSearchResultLinks();
        Assert.assertFalse(searchAllResultLinks.stream().anyMatch(link -> link.contains("random")));
    }
}

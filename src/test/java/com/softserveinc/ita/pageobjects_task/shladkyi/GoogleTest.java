package com.softserveinc.ita.pageobjects_task.shladkyi;

import com.softserveinc.ita.shladkyi.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private List<String> searchResultFirstLink;
    private String searchTerm;

    @BeforeMethod
    public void doSearch() {
        searchTerm = "funny kitten";
        searchResultFirstLink = new GoogleHomePage()
                .open()
                .searchFor(searchTerm)
                .getSearchResultFirstLink(0);
    }

    @Test
    public void testValidGoogleSearch() {
        Assert.assertTrue(searchResultFirstLink.get(0).toLowerCase().contains(searchTerm));
    }

    @Test
    public void testInValidGoogleSearch() {
        Assert.assertFalse(searchResultFirstLink.contains("random"));
    }

}

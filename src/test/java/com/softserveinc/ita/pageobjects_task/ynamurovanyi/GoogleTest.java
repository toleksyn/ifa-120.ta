package com.softserveinc.ita.pageobjects_task.ynamurovanyi;

import com.softserveinc.ita.ynamurovanyi.GoogleHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class GoogleTest extends TestRunner {
    private List<String> searchResultsLinks;
    private String searchTerm = "funny kitten";

    @BeforeMethod
    public void doSearch() {
        searchResultsLinks = new GoogleHomePage()
                .open()
                .searchFor(searchTerm)
                .getSearchResultsLinks();
    }

    @Test
    public void checkIfFirstLinkTextContains() {
        assertTrue(searchResultsLinks.get(0).contains(searchTerm));
    }

}

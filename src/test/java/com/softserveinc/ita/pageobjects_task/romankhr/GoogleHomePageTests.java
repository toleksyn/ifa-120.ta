package com.softserveinc.ita.pageobjects_task.romankhr;

import com.softserveinc.ita.pageobjects_task.romankhr.BaseTest;
import com.softserveinc.ita.romankhr.pages.GoogleHomePage;
import com.softserveinc.ita.romankhr.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleHomePageTests extends BaseTest {
    private GoogleHomePage homePage;
    private SearchResultsPage searchResultsPage;
    private String searchTerm = "funny kitten";

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        homePage = new GoogleHomePage(driver);
    }


    @Test(groups = "main", suiteName = "ui")
    public void gitLoginTest() throws Exception {

        searchResultsPage = homePage
                .searchTermSearch(SearchResultsPage.class, searchTerm);

        List<String> searchResultsLinks = searchResultsPage.getSearchResultsLinks();
        System.out.println("--->" + searchResultsLinks.get(0).toLowerCase().toString());
        Assert.assertTrue(searchResultsLinks.get(0).toLowerCase().contains(searchTerm));
    }

}

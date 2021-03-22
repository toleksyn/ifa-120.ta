package com.softserveinc.ita.pageobjects_task.romankhr;


import com.softserveinc.ita.pageobjects_task.romankhr.BaseTest;
import com.softserveinc.ita.romankhr.pages.GoogleHomePage;
import com.softserveinc.ita.romankhr.pages.SearchResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class GoogleHomePageTests extends BaseTest {
    private GoogleHomePage homePage;
   private SearchResultsPage searchResultsPage;
   private String searchTerm="funny kitten";
//    private RepositoryPage repositoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        homePage = new GoogleHomePage(driver);
    }

    //   -----------------------------Test 1-----------------------------

    @Test(groups = "main", suiteName = "ui")
    public void gitLoginTest() throws Exception {

   searchResultsPage=homePage
           .searchTermSearch(SearchResultsPage.class, searchTerm);

    List<String> searchResultsLinks=searchResultsPage.getSearchResultsLinks();
    assertThat(searchResultsLinks.contains(searchTerm)).isTrue;
    }

}

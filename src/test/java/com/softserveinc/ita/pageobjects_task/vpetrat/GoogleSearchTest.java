package com.softserveinc.ita.pageobjects_task.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.vpetrat.pageobjects.GoogleHomePage;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleSearchTest extends TestRunner {
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomepage() {
        googleHomePage = new GoogleHomePage()
                .openHomePage();
    }

    @Test
    public void testGoogleSearchFirstResultLinkText() {
        String firstSearchResultLink = googleHomePage
                .searchFor("funny kitten")
                .getSearchResultLinkTextForIndex(0);
        Assert.assertTrue(firstSearchResultLink.contains("funny kitten"));
    }

    @Test
    public void testGoogleSearchAllResultLink() {
        List<String> searchResultLinks = googleHomePage
                .searchFor("smartphone")
                .getListOfSearchResultLinks();
        Assert.assertTrue(searchResultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

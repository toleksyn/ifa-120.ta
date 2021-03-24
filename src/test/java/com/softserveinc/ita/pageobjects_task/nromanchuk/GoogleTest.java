package com.softserveinc.ita.pageobjects_task.nromanchuk;

import com.softserveinc.ita.nromanchuk.GoogleHomePage;
import com.softserveinc.ita.nromanchuk.TestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void startTest() {
        googleHomePage = new GoogleHomePage().open();
    }

    @Test
    public void TestGoogleSearch() {
        String searchTerm = "funny kitten";
        String getFirstLinkText = googleHomePage
                .searchFor(searchTerm)
                .getFirstLinkText();
        Assert.assertTrue(getFirstLinkText.contains(searchTerm));
    }

    @Test
    public void TestWikiLinkInResults() {
        List<String> searchResultLinks = googleHomePage
                .searchFor("Smartphone")
                .resultsLinks();
        Assert.assertTrue(searchResultLinks.stream().anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}

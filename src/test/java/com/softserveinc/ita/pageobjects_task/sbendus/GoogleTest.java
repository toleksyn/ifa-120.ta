package com.softserveinc.ita.pageobjects_task.sbendus;

import com.softserveinc.ita.sbendus.pageobject.TestRunner;
import com.softserveinc.ita.sbendus.pageobject.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    @Test
    public void testGoogleSearch() {
        String googleSearchMessage = "Funny Kitten";
        String firstLink = new GoogleHomePage()
                .open()
                .searchFor(googleSearchMessage)
                .getSearchAllResultLinks();
        Assert.assertTrue(firstLink.contains(googleSearchMessage));
    }

    @Test
    public void testGoogleSearchLinks() {
        String googleSearchMessage = "Smartphone";
        List<String> resultLinks = new GoogleHomePage()
                .open()
                .searchFor(googleSearchMessage)
                .getListOfSearchResultLinks();
        Assert.assertTrue(resultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }
}


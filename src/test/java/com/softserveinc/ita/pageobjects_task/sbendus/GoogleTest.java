package com.softserveinc.ita.pageobjects_task.sbendus;

import com.softserveinc.ita.sbendus.pageobject.TestRunner;
import com.softserveinc.ita.sbendus.pageobject.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    @Test
    public void testGoogleSearch() {
        String googleSearchMessage = "funny kitten";
        String firstLink = new GoogleHomePage()
                .open()
                .searchFor(googleSearchMessage)
                .getSearchResultsLink();
        Assert.assertTrue(firstLink.contains(googleSearchMessage));
    }
}

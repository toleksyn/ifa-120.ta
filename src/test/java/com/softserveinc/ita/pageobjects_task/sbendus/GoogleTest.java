package com.softserveinc.ita.pageobjects_task.sbendus;

import com.softserveinc.ita.sbendus.pageobject.TestRunner;
import com.softserveinc.ita.sbendus.pageobject.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    private String googleSearchMessage = "funny kitten";

    @Test
    public void testGoogleSearch() {
        String getFirstLink = new GoogleHomePage()
                .open()
                .searchFor(googleSearchMessage)
                .getGoogleSearchFirstLink();
        Assert.assertTrue(getFirstLink.contains(googleSearchMessage));
    }
}

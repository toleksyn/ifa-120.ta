package com.softserveinc.ita.pageobjects_task.nromanchuk;

import com.softserveinc.ita.nromanchuk.GoogleHomePage;
import com.softserveinc.ita.nromanchuk.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    @Test
    public void TestGoogleSearch() {
        String searchTerm = "funny kitten";
        String getFirstLinkText = new GoogleHomePage()
                .open()
                .searchFor(searchTerm)
                .getFirstLinkText();
        Assert.assertTrue(getFirstLinkText.contains(searchTerm));
    }
}

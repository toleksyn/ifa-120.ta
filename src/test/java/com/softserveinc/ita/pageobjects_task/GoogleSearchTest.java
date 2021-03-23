package com.softserveinc.ita.pageobjects_task;

import com.softserveinc.ita.vPetrat.GoogleHomePage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GoogleSearchTest extends TestRunner {
    private String request;

    @Test
    public void searchRequestTest() {
        List<String> requestResultText = new GoogleHomePage().open().searchRequest(request).collectSearchResults();
        Assert.assertTrue(requestResultText.contains(request));
    }
}

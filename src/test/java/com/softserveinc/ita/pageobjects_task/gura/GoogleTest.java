package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleResultPage;
import org.junit.Test;

public class GoogleTest {
    private String searchText = "funny kitten";
    private GoogleHomePage googlePage = new GoogleHomePage();
    private GoogleResultPage resultPage = new GoogleResultPage();

    @Test
    public void testingSearch() {
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openGoogleSearch();
        googlePage.doSearch(searchText);
        System.out.println(resultPage.checkLinkHasText(0, "funny kittens"));
    }
}

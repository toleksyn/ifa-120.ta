package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleResultPage;
import org.junit.Test;

public class GoogleTest {
    private GoogleHomePage googlePage = new GoogleHomePage();
    private GoogleResultPage resultPage = new GoogleResultPage();

    public void testingSearch(int indexOfLink, String searchText, String checkingText) {
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openGoogleSearch();
        googlePage.doSearch(searchText);
        assert(resultPage.checkLinkHasText(indexOfLink, checkingText));
    }

    @Test
    public void testingSearchWithSearchTextFunnyKitten() {
        testingSearch(0, "funny kitten", "funnykitten");
    }

    @Test
    public void testingSearchWithSearchTextSmartphone() {
        testingSearch(0, "smartphone", "wikipedia");
    }
}

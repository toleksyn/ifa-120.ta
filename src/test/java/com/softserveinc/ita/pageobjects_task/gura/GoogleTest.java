package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleResultPage;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleTest {
    private GoogleHomePage googlePage = new GoogleHomePage();
    private GoogleResultPage resultPage = new GoogleResultPage();

    public void testingContainOfLink(int indexOfLink, String searchText, String textInLink) {
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openPage();
        googlePage.doSearch(searchText);
        assert(checkLinkHasText(indexOfLink, textInLink));
    }

    public boolean checkLinkHasText(int indexOfLink, String link) {
        if (link.contains(
                Stream.of(GoogleResultPage.getDriver()
                        .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                        .map(element -> element
                                .get(indexOfLink)
                                .getText())
                        .collect(Collectors
                                .joining()))) return true;
        else return false;
    }

    @Test
    public void testingSearchWithSearchTextFunnyKitten() {
        testingContainOfLink(0, "funny kitten", "funnykitten");
    }

    @Test
    public void testingSearchWithSearchTextSmartphone() {
        testingContainOfLink(0, "smartphone", "wikipedia");
    }
}

package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleResultPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleTest {
    private GoogleHomePage googlePage = new GoogleHomePage();
    private GoogleResultPage resultPage = new GoogleResultPage();

    @Before
    public void testingContainOfLink() {
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openPage();
    }

    public String extractTextFromLink(int indexOfLink) {
         return Stream.of(GoogleResultPage.getDriver()
                    .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                    .map(element -> element
                            .get(indexOfLink)
                            .getText())
                    .map(String::toString)
                    .collect(Collectors.joining());
    }

    public boolean linkHasText(String link, String contains) {
        if (link.contains(contains)) return true;
        else return false;
    }

    @Test
    public void testingSearchWithSearchTextFunnyKitten() {
        googlePage.doSearch("funny kittens");
        assert(linkHasText(extractTextFromLink(0), "funnykitten"));
    }

    @Test
    public void testingSearchWithSearchTextSmartphone() {
        googlePage.doSearch("smartphone");
        assert(linkHasText(extractTextFromLink(0), "wikipedia"));
    }

    @Test
    public void testingSearchOnGoogleImagePage() {
        googlePage.doSearch("funny kittens");
        resultPage.goToImagePage();
    }
}

package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleTest {
    private GoogleHomePage googlePage;
    private GoogleResultPage resultPage = new GoogleResultPage();
    private GoogleImagePage imagePage = new GoogleImagePage();

    @Before
    public void beforeTesting() {
        TestRunner.setDriver(new ChromeDriver());
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage = new GoogleHomePage();
        googlePage.openPage();
    }

//    @Before
    public void beforeTestingRemoteWebDriver() {
        TestRunner.setRemoteWebDriver(new ChromeDriver());
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openPageRemoteWebDriver();
    }

    private String extractTextFromLink(int indexOfLink) {
         return Stream.of(GoogleResultPage.getDriver()
                    .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                    .map(element -> element
                            .get(indexOfLink)
                            .getText())
                    .map(String::toString)
                    .collect(Collectors.joining());
    }

    @Test
    public void testingSearchWithSearchTextFunnyKitten() {
        googlePage.doSearch("funny kittens");
        Assert.assertTrue(extractTextFromLink(0).contains("funny kitten"));
    }

    @Test
    public void testingSearchWithSearchTextSmartphone() {
        googlePage.doSearch("smartphone");
        Assert.assertTrue(extractTextFromLink(0).contains("wikipedia"));
    }

    @Test
    public void testingSearchOnGoogleImagePage() {
        String searchText = "funny";
        googlePage.doSearch("funny kitten").goToImagePage();

        List<String> imagesTitle = imagePage
                .getImagesTitle();

        assert(imagesTitle.get(0).toLowerCase().contains(searchText));
        assert(imagesTitle.get(4).toLowerCase().contains(searchText));
        assert(imagePage.goToHomePageByLogo().checkIfPageOpened());
    }

}

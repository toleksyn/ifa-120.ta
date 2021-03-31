package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleImagePage;
import com.softserveinc.ita.gura.GoogleResultPage;
import com.softserveinc.ita.gura.TestRunner;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleTest {
    private GoogleHomePage googlePage = new GoogleHomePage();
    private GoogleResultPage resultPage = new GoogleResultPage();
    private GoogleImagePage imagePage = new GoogleImagePage();

//    @Before
    public void beforeTesting() {
        TestRunner.driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openPage();
    }

    @Before
    public void beforeTestingRemoteWebDriver() {
        TestRunner.remoteWebDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", ".C:\\chromedriver.exe");
        googlePage.openPageRemoteWebDriver();
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
        String searchText = "funny";
        googlePage.doSearch("funny kitten").goToImagePage();

        List<String> imagesTitle = imagePage
                .getImagesTitle();

        assert(imagesTitle.get(0).toLowerCase().contains(searchText));
        assert(imagesTitle.get(4).toLowerCase().contains(searchText));
        assert(imagePage.goToHomePageByLogo().checkIfPageOpened());
    }

    @Test
    public void testingHideGoogleLogo() {
        googlePage.hideGoogleLogo();

    }
}

package com.softserveinc.ita.pageobjects_task.sbendus;

import com.codeborne.selenide.ElementsCollection;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.sbendus.pageobject.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest extends TestRunner {

    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void startTest() {
        googleHomePage = new GoogleHomePage()
                .open();
    }

    @Test
    public void testGoogleSearch() {
        String googleSearchMessage = "Funny Kitten";
        ElementsCollection firstLink = googleHomePage
                .searchFor(googleSearchMessage)
                .getSearchResultLinkText(1);
        Assert.assertTrue(firstLink.contains(googleSearchMessage));
    }

    @Test
    public void testGoogleSearchLinks() {
        String googleSearchMessage = "Smartphone";
        List<String> resultLinks = googleHomePage
                .searchFor(googleSearchMessage)
                .getListOfSearchResultLinks();
        Assert.assertTrue(resultLinks
                .stream()
                .anyMatch(resultLink -> resultLink.contains("wikipedia")));
    }


//
//    @Test
//    public void testGoogleSearchImageResults() {
//        String googleSearchMessage = "Funny Kitten";
//        String verificationText = "Funny";
//        GoogleSearchImagePage googleSearchImagePage = googleHomePage
//                .searchFor(googleSearchMessage)
//                .navigateToGoogleSearchImagePage();
//        List<String> listOfSearchImages = googleSearchImagePage
//                .getListOfSearchTitleResults();
//        Assert.assertTrue(listOfSearchImages.size() > 10);
//        Assert.assertTrue(listOfSearchImages.get(0).contains(verificationText));
//        Assert.assertTrue(listOfSearchImages.get(4).contains(verificationText));
//        googleSearchImagePage.navigateToHomePageByLogo();
//        Assert.assertTrue(TestRunner.getDriver().getCurrentUrl().contains("https://www.google.com/"));
//    }
//

    @Test
    public void testGoogleSearchResultAmount() {
        String googleSearchMessage = "webdriver";
        Integer googleSearchResultAmount = googleHomePage
                .searchFor(googleSearchMessage)
                .getGoogleSearchResultAmount();
        Assert.assertTrue(googleSearchResultAmount > 5000000);
    }
}



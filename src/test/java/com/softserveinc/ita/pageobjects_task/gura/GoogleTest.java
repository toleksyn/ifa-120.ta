package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleImagePage;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class GoogleTest extends TestRunner {
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage();
        googleHomePage.openGoogleHomePage();
    }

    @org.testng.annotations.Test
    public void testSearchWithSearchTextFunnyKitten() {
        String linkText = googleHomePage.doSearch("funny kitten").getTextFromLink(0);
        Assert.assertTrue(linkText.contains("funny kitten"));
    }

    @org.testng.annotations.Test
    public void testSearchWithSearchTextSmartphone() {
        List<String> hrefTextLinksList = googleHomePage.doSearch("smartphone").getListOfSearchResultLinks();
        Assert.assertTrue(hrefTextLinksList.stream().anyMatch(link -> link.contains("wikipedia")));
    }

    @org.testng.annotations.Test
    public void testSearchOnGoogleImagePage() {
        String searchText = "funny";
        GoogleImagePage googleImagePage = googleHomePage.doSearch("funny kitten").goToImagePage();

        List<String> imagesTitle = new GoogleImagePage()
                .getImagesTitles();

        Assert.assertTrue(imagesTitle.get(0).toLowerCase().contains(searchText));
        Assert.assertTrue(imagesTitle.get(4).toLowerCase().contains(searchText));
        Assert.assertTrue(googleImagePage.goToHomePageByLogo().isPageOpened());
    }

    @org.testng.annotations.Test
    public void testHideGoogleLogo() {
        googleHomePage.hideGoogleLogo();
        Assert.assertTrue(googleHomePage.checkGoogleLogoVisibility());
    }
}

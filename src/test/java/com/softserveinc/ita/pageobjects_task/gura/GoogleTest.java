package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleImagePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.List;

public class GoogleTest extends TestRunner {
    private GoogleHomePage googleHomePage = new GoogleHomePage();

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage.openGoogleHomePage();
    }

    @Test
    public void testSearchFirstLinkText() {
        String searchText = "funny kitten";
        String linkText = googleHomePage.doSearch(searchText).getTextFromLink(0);
        Assert.assertTrue(linkText.contains(searchText));
    }

    @Test
    public void testSearchWithSearchTextSmartphone() {
        List<String> hrefTextLinksList = googleHomePage.doSearch("smartphone").getListOfSearchResultLinks();
        Assert.assertTrue(hrefTextLinksList.stream().anyMatch(link -> link.contains("wikipedia")));
    }

    @Test
    public void testSearchOnGoogleImagePage() {
        String searchText = "funny";
        GoogleImagePage googleImagePage = googleHomePage.doSearch("funny kitten").goToImagePage();

        String link = googleImagePage.getGoogleLogoLink();
        List<String> imagesTitle = new GoogleImagePage().getImagesTitles();

        Assert.assertTrue(imagesTitle.get(0).toLowerCase().contains(searchText));
        Assert.assertTrue(imagesTitle.get(4).toLowerCase().contains(searchText));
        googleImagePage.goToHomePageByLogo();
        Assert.assertTrue(link.equals(TestRunner.getDriver().getCurrentUrl()));
    }

    @Test
    public void testHideGoogleLogo() {
        googleHomePage.hideGoogleLogo();
        Assert.assertTrue(googleHomePage.checkGoogleLogoVisibility());
    }
}

package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleImagePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.List;
import java.util.Locale;

public class GoogleTest {
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage().openGoogleHomePage();
    }

    @Test
    public void testSearchFirstLinkText() {
        String searchText = "funny kitten";
        String linkText = googleHomePage.doSearch(searchText).getTextFromLink(0).toLowerCase(Locale.ROOT);
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
        List<String> imagesTitles = new GoogleImagePage().getImagesTitles(10);

        Assert.assertTrue(imagesTitles.get(0).toLowerCase().contains(searchText));
        Assert.assertTrue(imagesTitles.get(4).toLowerCase().contains(searchText));
        googleImagePage.goToHomePageByLogo();
        Assert.assertTrue(link.equals(TestRunner.getDriver().getCurrentUrl()));
    }

    @Test
    public void testHideGoogleLogo() {
        googleHomePage.hideGoogleLogo();
        Assert.assertTrue(googleHomePage.checkGoogleLogoVisibility());
    }
}

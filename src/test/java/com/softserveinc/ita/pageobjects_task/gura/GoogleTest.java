package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.gura.*;
import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;

public class GoogleTest {
    private GoogleHomePage googleHomePage;

    @BeforeClass
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage().openGoogleHomePage();
    }

    @Test
    public void testSearchWithSearchTextFunnyKitten() {
        String linkText = googleHomePage.doSearch("funny kittens").getTextFromLink(0);
        Assert.assertTrue(linkText.contains("funny kitten"));
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

        List<String> imagesTitle = new GoogleImagePage()
                .getImagesTitles();

        Assert.assertTrue(imagesTitle.get(0).toLowerCase().contains(searchText));
        Assert.assertTrue(imagesTitle.get(4).toLowerCase().contains(searchText));
        Assert.assertTrue(googleImagePage.goToHomePageByLogo().checkIfPageOpened());
    }

    @Test
    public void testHideGoogleLogo() {
        googleHomePage.hideGoogleLogo();
        Assert.assertTrue(googleHomePage.checkGoogleLogoVisibility("visibility:hidden"));
    }
}

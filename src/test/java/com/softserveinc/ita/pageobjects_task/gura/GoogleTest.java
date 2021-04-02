package com.softserveinc.ita.pageobjects_task.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.gura.GoogleHomePage;
import com.softserveinc.ita.gura.GoogleImagePage;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.List;

public class GoogleTest extends TestRunner {
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void openGoogleHomePage() {
        googleHomePage = new GoogleHomePage();
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
        googleHomePage.doSearch("funny kitten");
        GoogleImagePage googleImagePage = new GoogleHomePage().goToImagePage();

        String link = TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).getAttribute("href");
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

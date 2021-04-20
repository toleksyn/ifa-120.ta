package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static org.testng.Assert.assertTrue;

public class HamburgerTest extends TestRunner {

    private HamburgerPage hamburgerPage;

    @BeforeMethod
    public void openHamburgerPage() {
        hamburgerPage = new HomePage()
                .openHomePage()
                .getHeaderPage()
                .openHamburgerPage();
    }

    @Test
    public void testLanguageChanging() {
        var homePage = hamburgerPage.changeLanguage();
        assertTrue(title().contains("Интернет-магазин"), "Title should contains 'Интернет-магазин'");
        assertTrue(homePage.getHeaderPage().isSearchButtonVisible(), "Search button should be visible");
    }
}

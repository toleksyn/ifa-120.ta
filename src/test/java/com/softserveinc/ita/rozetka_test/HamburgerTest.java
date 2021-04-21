package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerBar;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.LanguageOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static java.lang.String.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HamburgerTest extends TestRunner {

    private HamburgerBar hamburgerBar;

    @BeforeMethod
    public void openHamburgerBar() {
        hamburgerBar = new HomePage()
                .openHomePage()
                .getHeader()
                .openHamburgerBar();
    }

    @Test
    public void testLanguageChanging() {
        var homePage = hamburgerBar.switchLanguage(LanguageOption.RU);
        var expectedTitle = "Интернет-магазин";
        assertTrue(title().contains(expectedTitle), format("Title should contains '%s'", expectedTitle));
        assertTrue(homePage.getHeader().isSearchButtonDisplayed(), "Search button should be visible");
    }

    @Test
    public void testContactsButton() {
        var contactsPage = hamburgerBar.openContactsPage();
        var expectedTitle = "Контакти";
        assertEquals(contactsPage.getPageTitle(), expectedTitle, format("Page title should be '%s'", expectedTitle));
        assertTrue(contactsPage.isContactsSectionDisplayed(), "Contacts section should be visible");
    }
}

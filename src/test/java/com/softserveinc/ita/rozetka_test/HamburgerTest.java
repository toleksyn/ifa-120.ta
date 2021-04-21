package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static java.lang.String.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HamburgerTest extends TestRunner {

    private HamburgerPage hamburgerPage;

    @BeforeMethod
    public void openHamburgerPage() {
        hamburgerPage = new HomePage()
                .openHomePage()
                .getHeader()
                .openHamburgerPage();
    }

    @Test
    public void testLanguageChanging() {
        var homePage = hamburgerPage.switchLanguage();
        var expectedTitle = "Интернет-магазин";
        assertTrue(title().contains(expectedTitle), format("Title should contains '%s'", expectedTitle));
        assertTrue(homePage.getHeader().isSearchButtonVisible(), "Search button should be visible");
    }

    @Test
    public void testContactsButton() {
        var contactsPage = hamburgerPage.openContactsPage();
        var expectedTitle = "Контакти";
        assertEquals(contactsPage.getPageTitle(), expectedTitle, format("Page title should be '%s'", expectedTitle));
        assertTrue(contactsPage.isContactsSectionVisible(), "Contacts section should be visible");
    }
}

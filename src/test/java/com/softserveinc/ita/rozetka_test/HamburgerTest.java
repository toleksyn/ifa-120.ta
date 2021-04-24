package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.modules.HamburgerBar;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.enums.LanguageOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.*;

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
    public void testLanguageSwitching() {
        var homePage = hamburgerBar.switchLanguage(LanguageOption.RU);
        var searchFieldText = homePage
                .getHeader()
                .getSearchFieldText();
        assertEquals(searchFieldText, "Я ищу...", "Incorrect search field text");
        var categoryNames = homePage
                .getCatalogMenu()
                .getCategoryNames();
        var areCategoryNamesUkrainian = categoryNames
                .stream()
                .anyMatch(name -> name.contains("і"));
        assertFalse(areCategoryNamesUkrainian, "Category names should be translated");
    }

    @Test
    public void testContactsInformation() {
        var contactsPage = hamburgerBar.openContactsPage();
        var expectedTitle = "Контакти";
        assertEquals(contactsPage.getPageTitle(), expectedTitle, format("Page title should be '%s'", expectedTitle));
        var arePhoneNumbersCorrect = contactsPage
                .getPhoneNumbers()
                .stream()
                .allMatch(phoneNumber -> phoneNumber.contains("044"));
        assertTrue(arePhoneNumbersCorrect, "Phone numbers should be correct");
    }
}

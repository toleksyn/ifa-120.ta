package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.page_objects.HamburgerBar;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import com.softserveinc.ita.rozetka.page_objects.LanguageOption;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.*;
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
                .getLeftSidebar()
                .getCategoryNames();
        var isCategoryNamesUkrainian = categoryNames
                .stream()
                .anyMatch(name -> name.contains("і"));
        assertFalse(isCategoryNamesUkrainian, "Category names should be translated");
    }

    @Test
    public void testContactsInformation() {
        var contactsPage = hamburgerBar.openContactsPage();
        var expectedTitle = "Контакти";
        assertEquals(contactsPage.getPageTitle(), expectedTitle, format("Page title should be '%s'", expectedTitle));
        var isPhoneNumbersCorrect = contactsPage
                .getPhoneNumbers()
                .stream()
                .allMatch(phoneNumber -> phoneNumber.contains("044"));
        assertTrue(isPhoneNumbersCorrect, "Phone numbers should be correct");
    }
}

package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.HamburgerBar;
import com.softserveinc.ita.rozetka.enums.LanguageOption;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
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

    @Test
    public void testHelpCenterInformation() {
        var helpCenterPage = hamburgerBar.openHelpCenterPage();
        var expectedHeaderText = "Довідковий центр";
        var isHeaderTextCorrect = helpCenterPage
                .getHeaderText()
                .contains(expectedHeaderText);
        assertTrue(isHeaderTextCorrect, format("Text in header should contain '%s'", expectedHeaderText));
        var isHelpCategoryListEmpty = helpCenterPage
                .getHelpCategoryList()
                .isEmpty();
        assertFalse(isHelpCategoryListEmpty, "Help category list shouldn't be empty");
        var sectionsTitles = helpCenterPage
                .openPaymentHelpCategoryPage()
                .getSectionsTitleList();
        var firstSectionsTitle = sectionsTitles.get(0);
        var expectedFirstSectionTitle = "Оплата";
        assertTrue(firstSectionsTitle.contains(expectedFirstSectionTitle),
                format("First section's title should be '%s'", expectedFirstSectionTitle));
        var secondSectionsTitle = sectionsTitles.get(1);
        var expectedSecondSectionsTitle = "Кредитування та розсрочка";
        assertTrue(secondSectionsTitle.contains(expectedSecondSectionsTitle),
                format("Second section's title should be '%s'", expectedSecondSectionsTitle));
    }
}

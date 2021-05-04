package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static org.testng.Assert.*;

public class FooterTest extends TestRunner {

    private Footer footer;

    @BeforeMethod
    public void openFooter() {
        footer = new HomePage()
                .openHomePage()
                .getFooter();
    }

    @Test
    public void testProductCannotBeTrackedWhenNotLoggedIn() {
        var productTrackingPage = footer.openProductTrackingPage();
        var pageHeaderText = productTrackingPage.getHeaderText();
        assertEquals(pageHeaderText, "Вхід", "Incorrect page opened");
        assertEquals(title(), "ROZETKA — Мої замовлення | Особистий кабінет", "Incorrect page opened");

        var hamburgerBar = productTrackingPage.openHamburgerBar();
        var sideMenuAuthText = hamburgerBar.getSideMenuAuthText();
        assertTrue(sideMenuAuthText.contains("Авторизуйтесь для отримання розширених можливостей"), "You should not be logged in");
        hamburgerBar.closeBar();

        var isLoginAllowed = productTrackingPage.loginWithBlankCredentials().isBlankCredentialsAllowed();
        assertFalse(isLoginAllowed, "Blank credentials should not be allowed");
        assertNotEquals(pageHeaderText, "Мої замовлення", "Incorrect page opened");
        assertEquals(pageHeaderText, "Вхід", "Incorrect page opened");
    }

    @Test
    public void testOpenPagesByLinks() {
        var deliveryHelpPage = footer.openDeliveryHelpPage();
        var pageHeaderText = deliveryHelpPage.getHeaderText();
        assertEquals(pageHeaderText, "Хочу зробити замовлення. Коли його зможуть доставити?", "Incorrect page opened");
        assertEquals(title(), "Хочу зробити замовлення. Коли його зможуть доставити? – Допомога", "Incorrect page opened");
        var helpItemsNamesList = deliveryHelpPage.getHelpItemsNamesList();
        assertTrue(helpItemsNamesList.contains("Як діє кур’єрська доставка?"),
                "'Як діє кур’єрська доставка?' item should be present in side menu");
        assertTrue(helpItemsNamesList.contains("Як діє доставка товарів продавця Rozetka до відділень служб доставки?"),
                "'Як діє доставка товарів продавця Rozetka до відділень служб доставки?' item should be present in side menu");

        openFooter();
        var contactsPage = footer.openContactsPage();
        assertEquals(contactsPage.getPageTitle(), "Контакти", "Incorrect page opened");
        var arePhoneNumbersCorrect = contactsPage
                .getPhoneNumbers()
                .stream()
                .allMatch(phoneNumber -> phoneNumber.contains("044"));
        assertTrue(arePhoneNumbersCorrect, "Phone numbers should begin with '044'");

        openFooter();
        var productsExchangePage = footer.openProductsExchangePage();
        var exchangePageHeaderText = productsExchangePage.getHeaderText();
        assertTrue(exchangePageHeaderText.contains("Оновлюйте техніку до нової"), "Incorrect page opened");
        var exchangeContactsText = productsExchangePage.getExchangeContactsText();
        assertTrue(exchangeContactsText.contains("Пункти прийому ROZETKA Обмін"), "Incorrect page opened");

        openFooter();
        var partnershipPage = footer.openPartnershipPage();
        var partnershipPageHeaderText = partnershipPage.getHeaderText();
        assertTrue(partnershipPageHeaderText.contains("Як стати новим партнером компанії «Розетка»"), "Incorrect page opened");
        var partnershipArticleText = partnershipPage.getPartnershipArticleText();
        assertTrue(partnershipArticleText.contains("Опис товарів в електронному вигляді"), "Incorrect page opened");
    }
}

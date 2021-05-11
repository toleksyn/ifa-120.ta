package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static com.softserveinc.ita.rozetka.enums.DeviceSurfaceState.QUITE_NOTICEABLE;
import static com.softserveinc.ita.rozetka.enums.DeviceSurfaceState.STRONG_SCRATCHES;
import static com.softserveinc.ita.rozetka.enums.ExchangedDeviceType.SMARTPHONE;
import static com.softserveinc.ita.rozetka.enums.YesNoAnswer.Yes;
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

        var isLoginAllowed = productTrackingPage.login().areBlankCredentialsAllowed();
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
        deliveryHelpPage.openFooter();

        var contactsPage = footer.openContactsPage();
        assertEquals(contactsPage.getPageTitle(), "Контакти", "Incorrect page opened");
        var arePhoneNumbersCorrect = contactsPage
                .getPhoneNumbers()
                .stream()
                .allMatch(phoneNumber -> phoneNumber.contains("044"));
        assertTrue(arePhoneNumbersCorrect, "Phone numbers should begin with '044'");
        contactsPage.openFooter();

        var deviceExchangePage = footer.openDeviceExchangePage();
        var exchangePageHeaderText = deviceExchangePage.getHeaderText();
        assertTrue(exchangePageHeaderText.contains("Оновлюйте техніку до нової"), "Incorrect page opened");
        var exchangeContactsText = deviceExchangePage.getExchangeContactsText();
        assertTrue(exchangeContactsText.contains("Пункти прийому ROZETKA Обмін"), "Incorrect page opened");
        deviceExchangePage.openFooter();

        var partnershipPage = footer.openPartnershipPage();
        var partnershipPageHeaderText = partnershipPage.getHeaderText();
        assertTrue(partnershipPageHeaderText.contains("Як стати новим партнером компанії «Розетка»"), "Incorrect page opened");
        var partnershipArticleText = partnershipPage.getPartnershipArticleText();
        assertTrue(partnershipArticleText.contains("Опис товарів в електронному вигляді"), "Incorrect page opened");
    }

    @Test
    public void testDeviceForExchangeCostCalc() {
        var valuatedExchangeCost = footer
                .openDeviceExchangePage()
                .scrollToValueCalculatorSection()
                .setDeviceType(SMARTPHONE)
                .setBrandName("Xiaomi")
                .setDeviceName("Redmi 6A 2/32")
                .setCanDeviceBePoweredOn(Yes)
                .setIsDeviceScreenFullyOperational(Yes)
                .setAreAllDeviceFunctionsWork(Yes)
                .setScreenState(QUITE_NOTICEABLE)
                .setCoverState(STRONG_SCRATCHES)
                .getExchangeCost();
        assertEquals(valuatedExchangeCost, "650 ₴", "Incorrect result");
    }
}
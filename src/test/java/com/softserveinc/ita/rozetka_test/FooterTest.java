package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FooterTest extends TestRunner {

    private Footer footerBar;

    @BeforeMethod
    public void openFooterBar() {
        footerBar = new HomePage()
                .openHomePage()
                .getFooter();
    }

    @Test
    public void testBanningOrderTrackingWithoutLogin() {
        var productTrackingPage = footerBar.openTrackingPage();
        var pageTitle = productTrackingPage.getPageTitle();
        var pageHeader = productTrackingPage.getPageHeader();
        assertEquals(pageTitle, "ROZETKA — Мої замовлення | Особистий кабінет");

        var hamburgerBar = productTrackingPage.openHamburgerBar();
        var sideMenuAuthText = hamburgerBar.getSideMenuAuthText();
        assertTrue(sideMenuAuthText.contains("Авторизуйтесь для отримання розширених можливостей"));
        hamburgerBar.closeBar();

        assertFalse(productTrackingPage.isBlankCredentialsAllowed());
        assertNotEquals(pageHeader, "Мої замовлення");
        assertEquals(pageHeader, "Вхід");
    }
}

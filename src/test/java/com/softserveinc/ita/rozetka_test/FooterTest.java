package com.softserveinc.ita.rozetka_test;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        var pageTitle = productTrackingPage.getPageTitle();
        var pageHeader = productTrackingPage.getPageHeader();
        assertEquals(pageTitle, "ROZETKA — Мої замовлення | Особистий кабінет", "Incorrect page opened");

        var hamburgerBar = productTrackingPage.openHamburgerBar();
        var sideMenuAuthText = hamburgerBar.getSideMenuAuthText();
        assertTrue(sideMenuAuthText.contains("Авторизуйтесь для отримання розширених можливостей"), "You should not be logged in");
        hamburgerBar.closeBar();

        var isLoginAllowed = productTrackingPage.loginWithBlankCredentials().isBlankCredentialsAllowed();
        assertFalse(isLoginAllowed, "Blank credentials should not be allowed");
        assertNotEquals(pageHeader, "Мої замовлення", "Incorrect page opened");
        assertEquals(pageHeader, "Вхід", "Incorrect page opened");
    }
}

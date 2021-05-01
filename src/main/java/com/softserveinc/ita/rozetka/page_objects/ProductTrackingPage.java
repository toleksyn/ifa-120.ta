package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.HamburgerBar;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.title;

public class ProductTrackingPage extends BasePage {

    public String getPageTitle() {
        return title();
    }

    public String getPageHeader() {
        return $x("//h1").text();
    }

    @Step("ProductTrackingPage: open hamburger bar")
    public HamburgerBar openHamburgerBar() {
        $x("(//button[@class='header__button'])").click();
        return new HamburgerBar();
    }

    public boolean isBlankCredentialsAllowed() {
        var errorMessage = $x("//*[contains(@class, 'error-message')]").text();
        return !errorMessage.equals("Введено невірну адресу ел. пошти або номер телефону");
    }

    @Step("ProductTrackingPage: attempt to login with blank credentials")
    public ProductTrackingPage loginWithBlankCredentials() {
        $x("//button[contains(@class, 'button button--large')]").click();
        return this;
    }
}

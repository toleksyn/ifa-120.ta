package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Footer {
    //This is mock class for future test cases

    @Step("Footer: open product tracking page")
    public ProductTrackingPage openProductTrackingPage() {
        $x("//a[contains(@href, '/profile/account/')]").click();
        return new ProductTrackingPage();
    }

    @Step("Footer: open delivery help page")
    public DeliveryHelpPage openDeliveryHelpPage() {
        $x("(//li[contains(@class, 'main-links__')]//a)[1]").click();
        return new DeliveryHelpPage();
    }

    @Step("Footer: open Contacts page")
    public ContactsPage openContactsPage() {
        $x("(//li[contains(@class, 'main-links__')]//a)[10]").click();
        return new ContactsPage();
    }

    @Step("Footer: open exchange of products page")
    public DeviceExchangePage openDeviceExchangePage() {
        $x("(//li[contains(@class, 'main-links__')]//a)[14]").click();
        return new DeviceExchangePage();
    }

    @Step("Footer: open partnership page")
    public PartnershipPage openPartnershipPage() {
        $x("(//li[contains(@class, 'main-links__')]//a)[18]").click();
        return new PartnershipPage();
    }

}
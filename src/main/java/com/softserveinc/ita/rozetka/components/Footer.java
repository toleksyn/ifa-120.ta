package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ProductTrackingPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Footer {
    //This is mock class for future test cases

    @Step("Footer: open product tracking page")
    public ProductTrackingPage openProductTrackingPage() {
        $x("//a[contains(@href, '/profile/account/')]").click();
        return new ProductTrackingPage();
    }
}

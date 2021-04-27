package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DeliveryCityPage {
    public String getHeaderText() {
        return $x("//h3[@class='modal__heading']").text();
    }

    @Step("Delivery city page: close page")
    public DeliveryCityPage closeDeliveryCityPage() {
        $x("//*[@class='modal__close']").click();
        return this;
    }

    @Step("Delivery city page: submit delivery city")
    public DeliveryCityPage submitDeliveryCity() {
        $x("(//button[contains(@class,'button_color_green')])[3]").click();
        return this;
    }

    public boolean isSubmitButtonDisplayed() {
        return $x("(//button[contains(@class,'button_color_green')])[3]").isDisplayed();
    }
}

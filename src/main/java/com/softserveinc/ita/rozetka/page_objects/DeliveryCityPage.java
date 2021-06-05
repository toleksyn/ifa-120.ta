package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DeliveryCityPage {
    public String getHeaderText() {
        return $x("//h3[@class='modal__heading']").text();
    }

    @Step("Delivery city page: close page")
    public OrderPage closeDeliveryCityPage() {
        $x("//*[contains(@class, 'modal__close')]").click();
        return new OrderPage();
    }

    @Step("Delivery city page: submit delivery city")
    public DeliveryCityPage submitDeliveryCity() {
        $x("(//button[contains(@class,'button_color_green')])[3]").click();
        return this;
    }

    public boolean isSubmitButtonDisplayed() {
        return $x("(//button[contains(@class,'button_color_green')])[3]").isDisplayed();
    }

    @Step("Delivery city page: set delivery city {city}")
    public DeliveryCityPage setDeliveryCity(String city) {
        $x("(//input[@name= 'search'])[3]").setValue(city);
        $x("//li[contains(@class, 'autocomplete__item dialog_list')]").click();
        return this;
    }
}

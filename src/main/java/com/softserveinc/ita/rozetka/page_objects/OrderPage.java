package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public OrderPage setSurname(String surname) {
        $x("//div[@class='form__row js-surname']/input").setValue(surname);
        return this;
    }

    public OrderPage setName(String name) {
        $x("//div[@class='form__row js-name']/input").setValue(name);
        return this;
    }

    public BasketPage startEditingProductsInBasket() {
        $x("//*[@class='button button_with_icon button_type_link checkout-product__edit-button']").click();
        return new BasketPage();
    }

    public String getPageTitle() {
        return $x("//h1[@class='checkout-heading']").text();
    }

    public OrderPage setPhone(String phone) {
        $x("//input[@type='tel']").setValue(phone);
        return this;
    }

    public OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']").setValue(city)
                .click();
        $x(String.format("//div[@class='form__row js-city']//li[1]", city)).click();
        return this;
    }

    public boolean isDisplayedConfirmOrderButton() {
        return $x("//div[@class='checkout-total__buttons']").isDisplayed();
    }

    public String getSurname() {
        return $x("//div[@class='form__row js-surname']/input").getValue();
    }

    public String getName() {
        return $x("//div[@class='form__row js-name']/input").getValue();
    }

    public String getPhone() {
        return $x("//input[@type='tel']").getValue()
                .replaceAll(" ", "");
    }

    public String getCity() {
        return $x("//div[@class='form__row js-city']//input[@name='search']").getValue()
                .toLowerCase();
    }
}

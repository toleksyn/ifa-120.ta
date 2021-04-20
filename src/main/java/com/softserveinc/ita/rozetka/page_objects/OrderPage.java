package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public BasketPage startEditingProductsInBasket() {
        $x("//*[@class='button button_with_icon button_type_link checkout-product__edit-button']").click();
        return new BasketPage();
    }

    public String getPageTitle() {
        return $x("//h1[@class='checkout-heading']").text();
    }

    public boolean isConfirmOrderButtonDisplayed() {
        return $x("//div[@class='checkout-total__buttons']").isDisplayed();
    }

    public static String getSurname() {
        return $x("//div[@class='form__row js-surname']/input").getValue();
    }

    public static String getName() {
        return $x("//div[@class='form__row js-name']/input").getValue();
    }

    public static String getPhone() {
        return $x("//input[@type='tel']")
                .getValue()
                .replaceAll(" ", "");
    }

    public static String getCity() {
        return $x("//div[@class='form__row js-city']//input[@name='search']")
                .getValue()
                .toLowerCase();
    }

    public OrderPage setShippingAddress(ShippingAddress shippingAddress) {
        setName(shippingAddress.getName())
                .setSurName(shippingAddress.getSurname())
                .setPhoneNumber(shippingAddress.getPhone())
                .setCity(shippingAddress.getCity());
        return this;
    }

    private OrderPage setName(String name) {
        $x("//div[@class='form__row js-name']/input").setValue(name);
        return this;
    }

    private OrderPage setSurName(String surName) {
        $x("//div[@class='form__row js-surname']/input").setValue(surName);
        return this;
    }

    private OrderPage setPhoneNumber(String phoneNumber) {
        $x("//input[@type='tel']").setValue(phoneNumber);
        return this;
    }

    private OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']")
                .setValue(city)
                .click();
        $x("//div[@class='form__row js-city']//li[1]").click();
        return this;
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

public class OrderPage {

    public BasketPage startEditingProductsInBasket() {
        $x("//*[contains(@class, 'edit-button')]").click();
        return new BasketPage();
    }

    public String getHeaderText() {
        return $x("//h1[@class='checkout-heading']").text();
    }

    public boolean isConfirmOrderButtonDisplayed() {
        return $x("//div[@class='checkout-total__buttons']").isDisplayed();
    }

    public String getSurname() {
        return $x("//div[@class='form__row js-surname']/input").getValue();
    }

    public String getName() {
        return $x("//div[@class='form__row js-name']/input").getValue();
    }

    public String getPhone() {
        return $x("//input[@type='tel']")
                .getValue()
                .replaceAll(" ", "");
    }

    public String getCity() {
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

    public OrderPage setName(String name) {
        $x("//div[@class='form__row js-name']/input").setValue(name);
        return this;
    }

    public OrderPage setSurName(String surName) {
        $x("//div[@class='form__row js-surname']/input").setValue(surName);
        return this;
    }

    public OrderPage setPhoneNumber(String phoneNumber) {
        $x("//input[@type='tel']").setValue(phoneNumber);
        return this;
    }

    public OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']")
                .setValue(city)
                .click();
        $x("//div[@class='form__row js-city']//li[1]").click();
        return this;
    }

    public ProductPage openProductPage(int productNumber) {
        $x(format("(//*[@class='checkout-product__title-product'])[%d]", productNumber)).click();
        switchTo().window(1);
        return new ProductPage();
    }
}

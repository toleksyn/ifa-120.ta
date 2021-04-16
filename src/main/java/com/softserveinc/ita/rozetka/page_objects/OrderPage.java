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
        return $x("//input[@type='tel']").getValue()
                .replaceAll(" ", "");
    }

    public static String getCity() {
        return $x("//div[@class='form__row js-city']//input[@name='search']").getValue()
                .toLowerCase();
    }

    public OrderPage setShippingAddress(ShippingAddress shippingAddress) {
        $x("//div[@class='form__row js-surname']/input").setValue(shippingAddress.getSurname());
        $x("//div[@class='form__row js-name']/input").setValue(shippingAddress.getName());
        $x("//input[@type='tel']").setValue(shippingAddress.getPhone());
        $x("//div[@class='form__row js-city']//input[@name='search']").setValue(shippingAddress.getCity())
                .click();
        $x("//div[@class='form__row js-city']//li[1]").click();
        return this;
    }
}

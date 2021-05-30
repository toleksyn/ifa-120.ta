package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.enums.DeliveryOption;
import com.softserveinc.ita.rozetka.models.ShippingAddress;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class OrderPage {

    @Step("Order page: start editing products in basket")
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

    @Step("Order page: set name {name}")
    public OrderPage setName(String name) {
        $x("//div[@class='form__row js-name']/input").setValue(name);
        return this;
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

    @Step("Order page: set city {city}")
    public OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']")
                .setValue(city)
                .click();
        $x("//div[@class='form__row js-city']//li[1]").click();
        return this;
    }

    @Step("Order page: set shipping address {shippingAddress}")
    public OrderPage setShippingAddress(ShippingAddress shippingAddress) {
        setName(shippingAddress.getName())
                .setSurName(shippingAddress.getSurname())
                .setPhoneNumber(shippingAddress.getPhone())
                .setCity(shippingAddress.getCity());
        return this;
    }

    @Step("Order page: set surname {surName}")
    public OrderPage setSurName(String surName) {
        $x("//div[@class='form__row js-surname']/input").setValue(surName);
        return this;
    }

    @Step("Order page: set phone number {phoneNumber}")
    public OrderPage setPhoneNumber(String phoneNumber) {
        $x("//input[@type='tel']").setValue(phoneNumber);
        return this;
    }

    @Step("Order page: open product page by number {productNumber}")
    public ProductPage openProductPage(int productNumber) {
        $x(format("(//*[@class='checkout-product__title-product'])[%d]", productNumber)).click();
        switchTo().window(1);
        return new ProductPage();
    }

    @Step("Order page: set Delivery city {city} ")
    public OrderPage setDeliveryCity(String city) {
        $x("//button[contains(@class, 'button--link')]").click();
        $x("//div[@class='form__row js-city']//li[1]").click();
        return this;
    }

    public String getDeliveryRegion(String deliveryCity) {
        return $x("//span[contains(@class,'deliveries-heading')]").text();
    }

    @Step("Order page: open delivery City page")
    public DeliveryCityPage openDeliveryCityPage() {
        $x("//button[contains(@class, 'button--link')]").click();
        return new DeliveryCityPage();
    }

    @Step("Order page: set delivery option {deliveryOption}")
    public OrderPage setDeliveryOption(DeliveryOption deliveryOption) {
        $x(format("(//fieldset[contains(@class, 'checkout-block')]//ul)[4]/li//span[contains(text(), '%s')]", deliveryOption.getPartialLocatorDeliveryOption())).click();
        return this;
    }

    @Step("Order page: set delivery point by address")
    public String setDeliveryPointByAddress(String Street, String houseNbr) {
        $x("//button[contains(@class, 'dropdown-button')]").click();
        $x("//input[@id='searchPickupDelivery']").setValue(Street);
        $x(format("//div[contains(@class, 'autocomplete__item') and contains(text(), '%s')]", houseNbr)).click();
        return $x("//button[contains(@class, 'dropdown-button')]").text();
    }

    public String getDeliveryPointTimesheet() {
        return $x("//dl[contains(@class, 'schedule')]").text();
    }

}

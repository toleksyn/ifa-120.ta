package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

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
        $x("//*[contains(@class, 'edit-button')]").click();
        return new BasketPage();
    }

    public String getHeaderText() {
        return $x("//h1[@class='checkout-heading']").text();
    }

    public OrderPage setPhone(String phone) {
        $x("//input[@type='tel']").setValue(phone);
        return this;
    }

    public OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']").setValue(city).click();
        $x(format("(//li[contains(text(),'%s')])[1]", city)).click();
        return this;
    }

    public ProductPage openProductPage(int productNumber) {
        $x(format("(//*[@class='checkout-product__title-product'])[%d]", productNumber)).click();
        switchTo().window(1);
        return new ProductPage();
    }
}

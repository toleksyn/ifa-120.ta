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

    public BasketPage editItemInBasket() {
        $x("//*[@class='button button_with_icon button_type_link checkout-product__edit-button']").click();
        return new BasketPage();
    }

    public OrderPage setPhone(String phone) {
        $x("//input[@type='tel']").setValue(phone);
        return this;
    }

    public OrderPage setCity(String city) {
        $x("//div[@class='form__row js-city']//input[@name='search']").setValue(city).click();
        $x(String.format("(//li[contains(text(),'%s')])[1]", city)).click();
        return this;
    }
}

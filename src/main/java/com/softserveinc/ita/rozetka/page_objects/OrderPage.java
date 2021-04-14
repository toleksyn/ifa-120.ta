package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    public OrderPage fillAllInputFields(){
        $x("//div[@class='form__row js-surname']/input").setValue("Петренко");
        $x("//div[@class='form__row js-name']/input").setValue("Вася");
        $x("//div[@class='autocomplete']/input").setValue("Івано-Франківськ").click();
        $x("//div[@class='form__row js-phone']/input").setValue("77777777");
        return this;
    }

    public BasketPage editItemInBasket() {
        $x("//*[@class='button button_with_icon button_type_link checkout-product__edit-button']").click();
        return new BasketPage();
    }

    public boolean isThisIsOrderPage() {
        return $x("//h1[class='checkout-heading']").text().equals("Оформлення замовлення");
    }
}

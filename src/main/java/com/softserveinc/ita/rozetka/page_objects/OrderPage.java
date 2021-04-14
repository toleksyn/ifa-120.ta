package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public BasketPage startEditingProductInBasket() {
        $x("//*[@class='button button_with_icon button_type_link checkout-product__edit-button']").click();
        return new BasketPage();
    }

    public String getPageTitle() {
        return $x("//h1[@class='checkout-heading']").text();
    }
}

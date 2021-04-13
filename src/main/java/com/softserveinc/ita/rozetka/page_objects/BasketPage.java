package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {
    public String getFirstProductTitle() {
        return $x("//a[@class='cart-product__title']").text();
    }
}

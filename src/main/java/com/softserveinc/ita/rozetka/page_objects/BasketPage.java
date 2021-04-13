package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {
    public String getProductTitleByName(String productName) {
        return $x(String.format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
        }
    public void pushPlusItem() {
         $x("(//*[@class='button button_color_white button_size_medium cart-counter__button'])[2]").click();
    }

    public void pushMinusItem() {
        $x("(//*[@class='button button_color_white button_size_medium cart-counter__button'])[1]").click();
    }
}

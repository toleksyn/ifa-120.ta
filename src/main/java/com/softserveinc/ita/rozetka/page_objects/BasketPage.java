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

    public int increaseProductCount() {
        $x("(//*[@class='button button_color_white button_size_medium cart-counter__button'])[2]").click();
        return Integer.parseInt($x("//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid']").val());
    }

    public int decreaseProductCount() {
        $x("(//*[@class='button button_color_white button_size_medium cart-counter__button'])[1]").click();
        return Integer.parseInt($x("//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid']").val());
    }

    public int getProductCount() {
        return Integer.parseInt($x("//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid']").val());
    }

    public int getOrderProductPrice() {
        String ProductPrice = ($x("//*[@class='cart-product__price']").hover().text());
        return Integer.parseInt(ProductPrice.replace("₴", "").replace(" ", ""));
    }
}

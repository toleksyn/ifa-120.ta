package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class BasketPage {

    public String getProductTitleByName(String productName) {
        return $x(String.format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int numberOfProduct) {
        $x(format("((//*[@class='button button_color_white" +
                " button_size_medium cart-counter__button'])[2])[%d]",
                numberOfProduct)).click();
        return this;
    }

    public BasketPage decreaseProductCount(int numberOfProduct) {
        $x(format("((//*[@class='button button_color_white" +
                " button_size_medium cart-counter__button'])[1])[%d]",
                numberOfProduct)).click();
        return this;
    }

    public int getProductCount(int numberOfProduct) {
        return Integer.parseInt(String.valueOf($x(String.format("(//*[@class='cart-counter__input ng-untouched " +
                "ng-pristine ng-valid'])[%d]", numberOfProduct))
                .shouldNotBe(empty)
                .hover()
                .hover()
                .val()));
    }

    public int getOrderProductSum(int numberOfProduct) {
        String ProductPrice = $x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .shouldNotBe(empty)
                .hover()
                .text();
        return Integer.parseInt(ProductPrice.replace("₴", "").replace(" ", ""));
    }
}

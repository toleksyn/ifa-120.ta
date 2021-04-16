package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class BasketPage {

    public String getProductTitleByName(String productName) {
        return $x(String.format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int numberOfProduct) {
        var productSum = requireNonNull($x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .text());   //added for waiting the information in the window to be updated
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[2])[%d]",
                numberOfProduct)).click();
        $x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .shouldNotHave(text(productSum));   //added for waiting the information in the window to be updated
        return this;
    }

    public BasketPage decreaseProductCount(int numberOfProduct) {
        var productSum = requireNonNull($x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .text());  //added for waiting the information in the window to be updated
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]",
                numberOfProduct)).click();
        $x(format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .shouldNotHave(text(productSum));   //added for waiting the information in the window to be updated
        return this;
    }

    public int getProductCount(int numberOfProduct) {
        return Integer.parseInt(requireNonNull($x(String.format("(//*[@class='cart-counter__input ng-untouched " +
                "ng-pristine ng-valid'])[%d]", numberOfProduct))
                .val()));
    }

    public int getOrderProductSum(int numberOfProduct) {
        var productSum = requireNonNull($x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .text());
        return Integer.parseInt(productSum.replace("₴", "").replace(" ", ""));
    }
}

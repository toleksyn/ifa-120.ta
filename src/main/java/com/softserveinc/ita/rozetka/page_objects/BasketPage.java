package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    public String getProductTitleByName(String productName) {
        return $x(String.format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int number) {
        $x(String.format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[2])[%d]", number)).click();
        return this;
    }

    public BasketPage decreaseProductCount(int number) {
        $x(String.format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]", number)).click();
        return this;
    }

    public int getProductCount(int number) {
        $x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", number)).click();
        return Integer.parseInt($x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", number))
                .shouldBe(not(Condition.empty))
                .val());
    }

    public int getOrderProductSum(int number) {
        $x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", number)).click();
        String ProductPrice = ($x(String.format("(//*[@class='cart-product__price'])[%d]", number))
                .shouldBe(not(Condition.empty))
                .text());
        return Integer.parseInt(ProductPrice.replace("₴", "").replace(" ", ""));
    }
}

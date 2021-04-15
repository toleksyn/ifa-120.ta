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

    public BasketPage increaseProductCount(int numberOfProduct) {
        $x(String.format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[2])[%d]", numberOfProduct)).click();
        return this;
    }

    public BasketPage decreaseProductCount(int numberOfProduct) {
        $x(String.format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]", numberOfProduct)).click();
        return this;
    }

    public int getProductCount(int numberOfProduct) {
        $x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct)).click();
        return Integer.parseInt($x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct))
                .shouldBe(not(Condition.empty))
                .val());
    }

    public int getOrderProductSum(int numberOfProduct) {
        $x(String.format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct)).click();
        String ProductPrice = ($x(String.format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .shouldBe(not(Condition.empty))
                .text());
        return Integer.parseInt(ProductPrice.replace("₴", "").replace(" ", ""));
    }

    public BasketPage deleteProduct(int numberOfProduct) {
        $x(String.format("//button[@id='cartProductActions%d']", numberOfProduct - 1))
                .click();
        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']")
                .click();
        return this;
    }

    public boolean isBasketEmpty() {
        return ($x("//div[@class='cart-dummy']").exists());
    }

    public BasketPage deleteAllProducts() {
        while (!isBasketEmpty()) {
            deleteProduct(1);
        }
        return this;
    }
}

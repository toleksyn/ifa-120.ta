package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class BasketPage {

    public String getProductTitleByName(String productName) {
        return $x(format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int productNumber) {
        $x(format("((//*[@class='button button_color_white" +
                " button_size_medium cart-counter__button'])[2])[%d]", productNumber)).click();
        return this;
    }

    public BasketPage decreaseProductCount(int productNumber) {
        $x(format("((//*[@class='button button_color_white" +
                " button_size_medium cart-counter__button'])[1])[%d]", productNumber)).click();
        return this;
    }

    public int getProductCount(int productNumber) {
        $x(format("(//*[@class='cart-counter__input" +
                " ng-untouched ng-pristine ng-valid'])[%d]", productNumber)).click();
        return parseInt($x(format("(//*[@class='cart-counter__input" +
                " ng-untouched ng-pristine ng-valid'])[%d]", productNumber))
                .shouldBe(not(empty))
                .val());
    }

    public int getOrderProductSum(int productNumber) {
        $x(format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", productNumber)).click();
        var productPrice = ($x(format("(//*[@class='cart-product__price'])[%d]", productNumber))
                .shouldBe(not(empty))
                .text());
        return parseInt(productPrice.replaceAll("[^0-9]", ""));
    }

    public BasketPage deleteProduct(int productNumber) {
        var totalOrderPriceLocator = "//*[contains(@class,'m-p')]";
        var deleteButtonLocator = "//*[contains(@class,'k c')]";
        if (getUniqueProductsAmount() > 1) {
            var priceBeforeDeleting = $x(totalOrderPriceLocator).text();
            $x(format("//button[@id='cartProductActions%d']", productNumber - 1))
                    .click();
            $x(deleteButtonLocator)
                    .click();
            $x(totalOrderPriceLocator).shouldNotHave(text(priceBeforeDeleting));
        } else if (getUniqueProductsAmount() == 1) {
            $x(format("//button[@id='cartProductActions0']"))
                    .click();
            $x(deleteButtonLocator)
                    .click();
            $x("//div[@class='cart-dummy']").shouldBe(visible);
        }
        return this;
    }

    public boolean isBasketEmpty() {
        return ($x("//div[@class='cart-dummy']").exists());
    }

    public BasketPage deleteAllProducts() {
        int limiter = 50;
        while (limiter > 0 && !isBasketEmpty()) {
            deleteProduct(1);
            limiter--;
        }
        return this;
    }

    public int getUniqueProductsAmount() {
        if (!isBasketEmpty()) {
            return $$x("//*[contains(@class,'l c')]").size();
        } else {
            return 0;
        }
    }

    public int getTotalOrderSum() {
        if (!isBasketEmpty()) {
            return parseInt($x("//*[contains(@class,'m-p')]").text()
                    .replaceAll("[^0-9]", ""));
        } else {
            return 0;
        }
    }
}

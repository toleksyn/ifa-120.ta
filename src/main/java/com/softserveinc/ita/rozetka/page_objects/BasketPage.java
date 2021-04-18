package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Condition;

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

    public BasketPage increaseProductCount(int numberOfProduct) {
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[2])[%d]", numberOfProduct)).click();
        return this;
    }

    public BasketPage decreaseProductCount(int numberOfProduct) {
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]", numberOfProduct)).click();
        return this;
    }

    public int getProductCount(int numberOfProduct) {
        $x(format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct)).click();
        return parseInt($x(format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct))
                .shouldBe(not(Condition.empty))
                .val());
    }

    public int getOrderProductSum(int numberOfProduct) {
        $x(format("(//*[@class='cart-counter__input ng-untouched ng-pristine ng-valid'])[%d]", numberOfProduct)).click();
        String ProductPrice = ($x(format("(//*[@class='cart-product__price'])[%d]", numberOfProduct))
                .shouldBe(not(Condition.empty))
                .text());
        return parseInt(ProductPrice.replaceAll("[^0-9]", ""));
    }

    public BasketPage deleteProduct(int numberOfProduct) {
        var totalOrderPriceLocator = "//div[@class='cart-receipt__sum-price']";
        var deleteButtonLocator = "//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']";
        if (getUniqueProductsAmount() > 1) {
            var priceBeforeDeleting = $x(totalOrderPriceLocator).text();
            $x(format("//button[@id='cartProductActions%d']", numberOfProduct - 1))
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
        if (!isBasketEmpty()) {
            deleteProduct(1);
        }
        if (!isBasketEmpty()) {
            deleteAllProducts();
        }
        return this;
    }

    public int getUniqueProductsAmount() {
        if (!isBasketEmpty()) {
            return $$x("//button[@class='button button--white button--small context-menu__toggle']").size();
        } else {
            return 0;
        }
    }

    public int getTotalOrderSum() {
        if (!isBasketEmpty()) {
            return parseInt($x("//div[@class='cart-receipt__sum-price']").text()
                    .replaceAll("[^0-9]", ""));
        } else {
            return 0;
        }
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class BasketPage {

    public String getProductTitleByName(String productName) {
        return $x(format("//a[@class='cart-product__title' and contains(text(), '%s')]", productName)).text();
    }

    public OrderPage openOrderPage() {
        $x("//*[contains(@class,'cart-receipt__submit')]").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int productNumber) {
        var preIncreaseProductSumText = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[contains(@class, 'cart-counter__button')])[2])[%d]", productNumber)).click();
        // Checking var "preIncreaseProductSumText" in the next statement have been added to ensure updating information in the basket window
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preIncreaseProductSumText));
        return this;
    }

    public BasketPage decreaseProductCount(int productNumber) {
        if (!isDecreaseProductCountEnabled(productNumber)) {
            throw new IllegalStateException("Product count decreasing is disabled");
        }

        var preDecreaseProductSumText = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[contains(@class, 'cart-counter__button')])[1])[%d]", productNumber)).click();
        // Checking var "preDecreaseProductSumText" in the next statement have been added to ensure updating information in the basket window
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preDecreaseProductSumText));
        return this;
    }

    public int getProductCount(int productNumber) {
        return parseInt($x(format("(//*[contains(@class, 'cart-counter__input')])[%d]", productNumber)).val());
    }

    public int getOrderProductSum(int productNumber) {
        var productSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        return parseInt(productSum.replaceAll("[^0-9]", ""));
    }

    public boolean isDecreaseProductCountEnabled(int productNumber) {
        return $x(format("((//*[contains(@class, 'cart-counter__button')])[1])[%d]", productNumber)).isEnabled();
    }

    public BasketPage deleteProduct(int productNumber) {
        var totalOrderPriceLocator = "//*[contains(@class,'m-p')]";
        var deleteButtonLocator = "//*[contains(@class,'k c')]";
        if (getUniqueProductsAmount() > 1) {
            var priceBeforeDeleting = $x(totalOrderPriceLocator).text();
            $x(format("//button[@id='cartProductActions%d']", productNumber - 1)).click();
            $x(deleteButtonLocator).click();
            $x(totalOrderPriceLocator).shouldNotHave(text(priceBeforeDeleting));
        } else if (getUniqueProductsAmount() == 1) {
            $x(format("//button[@id='cartProductActions0']")).click();
            $x(deleteButtonLocator).click();
            $x("//div[@class='cart-dummy']").shouldBe(visible);
        }
        return this;
    }

    public boolean isBasketEmpty() {
        return $x("//div[@class='cart-dummy']").exists();
    }

    public BasketPage deleteAllProducts() {
        int tries = 50;
        while (tries > 0 && !isBasketEmpty()) {
            deleteProduct(1);
            tries--;
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
            return parseInt($x("//*[contains(@class,'m-p')]").text().replaceAll("[^0-9]", ""));
        } else {
            return 0;
        }
    }

    public ProductPage openProductPage(int productNumber) {
        $x(format("(//*[@class='cart-product__title'])[%d]", productNumber)).click();
        return new ProductPage();
    }
}
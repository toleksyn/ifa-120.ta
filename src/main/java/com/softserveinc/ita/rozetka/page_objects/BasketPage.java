package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Condition.text;
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
        var preIncreaseProductSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[contains(@class, 'cart-counter__button')])[2])[%d]", productNumber)).click();
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preIncreaseProductSum));
        return this;
    }

    public BasketPage decreaseProductCount(int productNumber) {
        if (!isDecreaseProductCountEnabled(productNumber)) {
            throw new IllegalStateException("Product count decreasing is disabled");
        }

        var preDecreaseProductSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[contains(@class, 'cart-counter__button')])[1])[%d]", productNumber)).click();
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preDecreaseProductSum));
        return this;
    }

    public int getProductCount(int productNumber) {
        return parseInt($x(format("(//*[contains(@class, 'cart-counter__input')])[%d]", productNumber)).val());
    }

    public int getOrderProductSum(int productNumber) {
        var productSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        return parseInt(productSum.replace("₴", "").replace(" ", ""));
    }

    public boolean isDecreaseProductCountEnabled(int productNumber) {
        return $x(format("((//*[contains(@class, 'cart-counter__button')])[1])[%d]", productNumber)).isEnabled();
    }
}

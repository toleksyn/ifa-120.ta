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
        $x("//*[@class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new OrderPage();
    }

    public BasketPage increaseProductCount(int productNumber) {
        var productSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[2])[%d]",
                productNumber)).click();
        /* checking var "productSum" in the next statement have been added to ensure updating information in the basket window */
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(productSum));
        return this;
    }

    public BasketPage decreaseProductCount(int productNumber) throws Exception {
        if (!isDecreaseProductCountEnabled(productNumber)) {
            throw new Exception("should not further reduction in the count of product items");
        }

        var productSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]",
                productNumber)).click();
        /* checking var "productSum" in the next statement have been added to ensure updating information in the basket window */
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(productSum));
        return this;
    }

    public int getProductCount(int productNumber) {
        return parseInt($x(format("(//*[@class='cart-counter__input ng-untouched " +
                "ng-pristine ng-valid'])[%d]", productNumber)).val());
    }

    public int getOrderProductSum(int productNumber) {
        var productSum = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        return parseInt(productSum.replace("₴", "").replace(" ", ""));
    }

    public boolean isDecreaseProductCountEnabled(int productNumber) {
        return $x(format("((//*[@class='button button_color_white button_size_medium cart-counter__button'])[1])[%d]",
                productNumber)).isEnabled();
    }
}

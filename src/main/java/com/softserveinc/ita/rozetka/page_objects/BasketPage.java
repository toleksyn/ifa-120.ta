package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
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
        // checking var "preIncreaseProductSumText" in the next statement have been added to ensure updating information in the basket window
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preIncreaseProductSumText));
        return this;
    }

    public BasketPage decreaseProductCount(int productNumber) {
        if (!isDecreaseProductCountEnabled(productNumber)) {
            throw new IllegalStateException("Product count decreasing is disabled");
        }

        var preDecreaseProductSumText = $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).text();
        $x(format("((//*[contains(@class, 'cart-counter__button')])[1])[%d]", productNumber)).click();
        // checking var "preDecreaseProductSumText" in the next statement have been added to ensure updating information in the basket window
        $x(format("(//*[@class='cart-product__price'])[%d]", productNumber)).shouldNotHave(text(preDecreaseProductSumText));
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

    public BasketPage deleteProduct(int productIndex) {
        $x(format("//button[@aria-controls='cartProductActions%d']", productIndex)).click();
        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
        return this;
    }

    public BasketPage restoreProduct(int productIndex) {
        Selenide.actions().moveToElement($x(format("(//button[@class='buy-button lite-tile__buy-button'])[%d]", productIndex))).click();
//        $x(format("(//button[@class='buy-button lite-tile__buy-button'])[%d]", productIndex)).click();
        return this;
    }

    public String getRecentlyViewedProductLink(int productIndex) {
        return $x(format("(//a[@class='lite-tile__title'])[%d]", productIndex)).text();
    }

    public String getProductLink(int productIndex) {
        return $x(format("(//a[@class='cart-product__title'])[%d]", productIndex)).text();
    }

    public BasketPage scrollDown() {
        switchTo();
        Selenide.executeJavaScript("window.scrollBy(0,1000)");
        return this;
    }
}

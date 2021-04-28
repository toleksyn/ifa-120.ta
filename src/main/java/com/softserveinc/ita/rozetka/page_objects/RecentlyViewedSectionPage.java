package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class RecentlyViewedSectionPage {
    @Step("Basket page: add product from recently viewed product block for product {productIndex}")
    public BasketPage addProductFromRecentlyViewedProducts(int productIndex) {
        Selenide.actions()
                .moveToElement($x(format("(//section[@class='recently-viewed ng-star-inserted']//button[@class='buy-button lite-tile__buy-button ng-star-inserted'])[%d]", productIndex)))
                .click();
        return new BasketPage();
    }

    @Step("Basket page: get recently viewed product title for product {productIndex}")
    public String getRecentlyViewedProductTitle(int productIndex) {
        return $x(format("(//section[@class='recently-viewed ng-star-inserted']//a[@class='lite-tile__title ng-star-inserted'])[%d]", productIndex)).text();
    }
}

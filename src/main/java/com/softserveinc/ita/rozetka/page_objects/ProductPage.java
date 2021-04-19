package com.softserveinc.ita.rozetka.page_objects;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class ProductPage extends BasePage {

    public String getProductTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public String getViewedProductName(int number) {
        return $x(format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number)).text();
    }

    public BasketPage addProductToBasket() {
        $x("//button[@class='buy-button button button_with_icon button_color_green button_size_large']")
                .hover()
                .click();
        return new BasketPage();
    }

    public String getProductTabsTitle() {
        return $x("//h2[@class='product-tabs__heading']").text();
    }

    public int getQuestionListSize() {
        return $$x("//div[@class='comment']")
                .shouldHave(sizeGreaterThan(0))
                .size();
    }

    public List<String> getCharacteristicsTexts() {
        return $$x("//dd/ul/li/*")
                .shouldHave(sizeGreaterThan(0))
                .texts();
    }

    public ProductPage openProductTab(ProductPageTab productPageTab) {
        var tabButton = productPageTab == ProductPageTab.DESCRIPTION ?
                $x("(//ul[@class='tabs__list']//a)[1]") :
                $x(format("//ul[@class='tabs__list']//a[contains(@href, '%s')]", productPageTab.getTabHrefIdentifier()));
        tabButton.click();
        return this;
    }

    public ProductPage addProductToWishList() {
        $x("//app-goods-wishlist[@class='product__favorites']//button[@class='wish-button js-wish-button']")
                .doubleClick();
        return this;
    }

    public CategoryPage openCategoryPageByName(String categoryName) {
        $x(String.format("//a[@class='breadcrumbs__link'] //span[contains(text(),'%s')]", categoryName)).click();
        return new CategoryPage();
    }

    public Integer getProductWithPreDiscountPrice() {
        var productWithPreDiscountPriceText = $x("//p[@class = 'product-prices__small']")
                .text();
        var productWithPreDiscountPrice = parseInt(productWithPreDiscountPriceText
                .substring(0, productWithPreDiscountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return productWithPreDiscountPrice;
    }

    public Integer getProductWithDiscountPrice() {
        var productWithDiscountPriceText = $x("//p[@class = 'product-prices__big product-prices__big_color_red']")
                .text();
        var productWithDiscountPrice = parseInt(productWithDiscountPriceText
                .substring(0, productWithDiscountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return productWithDiscountPrice;
    }
}
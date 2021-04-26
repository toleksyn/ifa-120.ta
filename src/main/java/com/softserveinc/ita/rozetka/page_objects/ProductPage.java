package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.enums.ProductPageTab;
import io.qameta.allure.Step;

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

    @Step("Product page: add product to basket")
    public BasketPage addProductToBasket() {
        $x("//button[contains(@class, 'button_size_large')]").hover().click();
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

    @Step("Product page: open product tab {productPageTab}")
    public ProductPage openProductTab(ProductPageTab productPageTab) {
        var tabButton = productPageTab == ProductPageTab.DESCRIPTION ?
                $x("(//ul[@class='tabs__list']//a)[1]") :
                $x(format("//ul[@class='tabs__list']//a[contains(@href, '%s')]", productPageTab.getTabHrefIdentifier()));
        tabButton.click();
        return this;
    }

    @Step("Product page: open category page by link {categoryName}")
    public CategoryPage openCategoryPageByLink(String categoryName) {
        $x(format("//a[@class='breadcrumbs__link'] //span[contains(text(),'%s')]", categoryName)).click();
        return new CategoryPage();
    }

    public Integer getPreDiscountPrice() {
        var preDiscountPriceText = $x("//p[contains(@class , 'product-prices__small')]").text();
        var preDiscountPrice = parseInt(preDiscountPriceText
                .substring(0, preDiscountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return preDiscountPrice;
    }

    public Integer getDiscountPrice() {
        var discountPriceText = $x("//p[contains(@class , 'product-prices__big')]").text();
        var discountPrice = parseInt(discountPriceText
                .substring(0, discountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return discountPrice;
    }

    public String getCharacteristicText(String characteristicType) {
        return $x(format("//div[contains(@class, 'characteristics-full__item') and .//span/text()='%s']//li/*",
                characteristicType)).text();
    }
    public CheaperTogetherMenu openCheaperTogetherMenu(){
        return new CheaperTogetherMenu();
    }
}
package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.enums.ProductPageTab;
import com.codeborne.selenide.Condition;
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

    public String getViewedProductName(int number) {
        return $x(format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number)).text();
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

    @Step("Product page: open category page by name {categoryName}")
    public CategoryPage openCategoryPageByName(String categoryName) {
        $x(format("//a[@class='breadcrumbs__link'] //span[contains(text(),'%s')]", categoryName)).click();
        return new CategoryPage();
    }

    public Integer getPreDiscountPrice() {
        var preDiscountPriceText = $x("//p[@class = 'product-prices__small']").text();
        var preDiscountPrice = parseInt(preDiscountPriceText
                .substring(0, preDiscountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return preDiscountPrice;
    }

    public Integer getDiscountPrice() {
        var discountPriceText = $x("//p[@class = 'product-prices__big "
                + "product-prices__big_color_red']").text();
        var discountPrice = parseInt(discountPriceText
                .substring(0, discountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return discountPrice;
    }

    @Step("Product page: rating is displayed")
    public boolean isRatingDisplayed() {
        return $x("//div[@class='product__rating']")
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    public String getCharacteristicText(String characteristicType) {
        return $x(format("//div[contains(@class, 'characteristics-full__item') and .//span/text()='%s']//li/*",
                characteristicType)).text();
    }

    @Step("Product page: open home page by logo")
    public HomePage openHomePageByLogo() {
        $x("//*[@class='header__logo']").click();
        return new HomePage();
    }

    @Step("Product page: warranty is displayed")
    public boolean isWarrantyDisplayed() {
        return $x("(//div[@class='product-about__block-heading product-delivery__heading'])[2]")
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    @Step("Product page: Premium Button is displayed")
    public boolean isPremiumButtonDisplayed() {
        return $x("//a[@class='button button--small button--gray']")
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    @Step("Product page: get Product code")
    public String getProductCode() {
        return $x("//p[@class='product__code detail-code']")
                .shouldBe(Condition.visible)
                .text()
                .replaceAll("Код:", "")
                .replaceAll(" ", "");
    }
}
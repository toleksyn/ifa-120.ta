package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
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

    public ProductPage openProductTab(ProductPageTab productPageTab) {
        var tabButton = productPageTab == ProductPageTab.DESCRIPTION ?
                $x("(//ul[@class='tabs__list']//a)[1]") :
                $x(format("//ul[@class='tabs__list']//a[contains(@href, '%s')]", productPageTab.getTabHrefIdentifier()));
        tabButton.click();
        return this;
    }

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

    public String getCharacteristicText(String characteristicType) {
        return $x(format("//div[@class='characteristics-full__item' and .//span/text()='%s']//li/*",
                characteristicType)).text();
    }

    public HomePage openHomePageByLogo() {
        $x("//*[@class='header__logo']").click();
        return new HomePage();
    }

    public List<String> getProductSectionsTitleList() {
        return $$x("//*[@class='product-tabs__heading']")
                .shouldHave(sizeGreaterThan(2))
                .texts();
    }

    public String getProductReviewCount() {
        return $x("//*[contains(@class,'product-comm')]//span[contains(@class,'product-t')]").text();
    }

    @Step("Product page: open Delivery City page")
    public DeliveryCityPage openDeliveryCityPage() {
        actions()
                .moveToElement($x("//div[contains(@class,'product-about__block-h')]//*[contains(@class,'button')]"))
                .click()
                .perform(); //manipulations to display the item, in the case of overlapping by advertising pop-up window
        return new DeliveryCityPage();
    }
}
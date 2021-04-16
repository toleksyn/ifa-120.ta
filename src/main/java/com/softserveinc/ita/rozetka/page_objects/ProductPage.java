package com.softserveinc.ita.rozetka.page_objects;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class ProductPage extends BasePage {

    public String getProductTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public String getViewedProductName(int number) {
        return $x(format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number))
                .text();
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
        $x(format("//li//a[@href='%s%s/']", getWebDriver().getCurrentUrl(), productPageTab.getTabPath())).click();
        return this;
    }
}
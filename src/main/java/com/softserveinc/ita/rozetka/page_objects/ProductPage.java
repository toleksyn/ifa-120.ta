package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.*;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    public String getProductTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public ElementsCollection getListOfProductTabs() {
        return $$x("//a[@class='tabs__link']");
    }

    public SelenideElement getViewedProduct(int number) {
        return $x(String.format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number));
    }

    public CategoryPage returnToCategoryPage(int index) {
        $$x("//a[@class='breadcrumbs__link']").get(index).click();
        return new CategoryPage();
    }

    public BasketPage addProductToBasket() {
        $x("//button[@class='buy-button button button_with_icon button_color_green button_size_large']").hover().click();
        return new BasketPage();
    }

    public ProductPage openProductTabByName(String tabName) {
        $x(String.format("//a[@class='tabs__link' and contains(text(),'%s')]", tabName)).click();
        return this;
    }

    public String getProductTabsTitle() {
        return $x("//h2[@class='product-tabs__heading']").text();
    }

    public int getQuestionListSize(int amountQuestionsItem) {
        return $$x("//div[@class='comment']")
                .shouldHave(CollectionCondition.sizeGreaterThan(amountQuestionsItem)).size();
    }

    public int getCharacteristicListSize(int amountCharacteristicsItem ){
        return $$x("//dd/ul/li")
                .shouldHave(CollectionCondition.sizeGreaterThan(amountCharacteristicsItem))
                .size();
    }
}
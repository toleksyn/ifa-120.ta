package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    public String getProductTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public ElementsCollection getListOfProductTabs() {
        return Selenide.$$x("//a[@class='tabs__link']");
    }

    public SelenideElement getViewedProduct(int number) {
        return $x(String.format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number));
    }

    public CategoryPage returnToCategoryPageByName(String categoryName) {
       $x(String.format("//a[@class='breadcrumbs__link'] //span[contains(text(),'%s')]", categoryName)).click();
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

    public String getCharacteristicDescriptionByIndex(int index) {
        return $x(String.format("(//ul[@class='characteristics-full__sub-list'])[%d]", index)).text();
    }

    public String getProductTabsTitle() {
        return $x("//h2[@class='product-tabs__heading']").text();
    }

    public List<SelenideElement> getCommentsList() {
        return $$x("//div[@class='comment']");
    }
}

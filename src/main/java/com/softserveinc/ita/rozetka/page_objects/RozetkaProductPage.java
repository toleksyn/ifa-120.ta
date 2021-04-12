package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RozetkaProductPage {

     public String getProductTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public ElementsCollection getListOfProductTabs() {
        return Selenide.$$x("//a[@class='tabs__link']");
    }

    public SelenideElement getViewedProduct(int number) {
        return $x(String.format("(//section[@class='recently-viewed']//a[@class='lite-tile__title'])[%d]", number));
    }

    public RozetkaCategoryPage returnToCategoryPage(int index) {
        Selenide.$$x("//a[@class='breadcrumbs__link']").get(index).click();
        return new RozetkaCategoryPage();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class ConcreteCategoryPage {

    public String getPageTitle() {
        return $x("//h1[@class='portal__heading']").text();
    }

    public ConcreteCategoryPage navigateToProductCategoryPage(String categoryName) {
         $x(String.format("//a[@class='tile-cats__heading tile-cats__heading_type_center' and contains(text(), '%s')]", categoryName))
               .click();
         return this;
    }


    public ProductPage navigateToProductByNumber(String productName) {
        $x(String.format("//span[@class='goods-tile__title'and contains(text(), '%s')]", productName)).click();
        return new ProductPage();
    }
}

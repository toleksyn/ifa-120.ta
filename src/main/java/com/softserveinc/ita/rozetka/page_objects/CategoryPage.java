package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage {

    public String getPageTitle() {
        return $x("//h1[@class='portal__heading']").text();
    }

    public CategoryPage navigateToProductCategoryPage(int categoryPage) {
        $x(String.format("//a[@class='tile-cats__heading tile-cats__heading_type_center'][%d]", categoryPage)).click();
        return new CategoryPage();
    }

    public ProductPage openToProductByNumber(int number) {
        $$x("//span[@class='goods-tile__title']").get(number).click();
        return new ProductPage();
    }
}

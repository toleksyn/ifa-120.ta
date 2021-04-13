package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {


    public CategoryPage navigateToProductCategoryPage(int index) {
        $$x("//a[@class='tile-cats__heading tile-cats__heading_type_center']").get(index).click();
        return new CategoryPage();
    }

    public ProductPage navigateToProductByNumber(int number) {
        $$x("//span[@class='goods-tile__title']").get(number).click();
        return new ProductPage();
    }

    public ProductsListPage navigateToProductsListPage(int index) {
        $x(String.format("(//a[@class='tile-cats__heading tile-cats__heading_type_center'])[%d]", index)).click();
        return new ProductsListPage();
    }

    public ProductsListPage navigateToProductsListPage(String productName) {
        $x(String.format("(//a[@class='tile-cats__heading tile-cats__heading_type_center' and contains(text(), '%s)])", productName))
                .click();
        return new ProductsListPage();
    }

    public String getPageTitle() {
        return $x("//h1[@class='portal__heading']").text();
    }

}

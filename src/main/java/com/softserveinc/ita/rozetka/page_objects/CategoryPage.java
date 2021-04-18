package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage extends BasePage {

    public CategoryPage openProductCategoryPage(String categoryName) {
        $x(format("//a[@class='tile-cats__heading tile-cats__heading_type_center'" +
                " and contains(text(), '%s')]", categoryName)).click();
        return new CategoryPage();
    }

    public ProductPage openProductByNumber(int number) {
        $x(format("(//span[@class='goods-tile__title'])[%d]", number)).click();
        return new ProductPage();
    }

    public ProductsListPage openProductsListPage(int index) {
        $x(format("(//a[@class='tile-cats__heading tile-cats__heading_type_center'])[%d]", index)).click();
        return new ProductsListPage();
    }

    public ProductsListPage openProductsListPage(String productCategoryName) {
        $x(format("//a[@class='tile-cats__heading tile-cats__heading_type_center'" +
                " and contains(text(), '%s')]", productCategoryName)).click();
        return new ProductsListPage();
    }

    public String getCategoryTitle() {
        return $x("//h1[@class='portal__heading']").text();
    }

}

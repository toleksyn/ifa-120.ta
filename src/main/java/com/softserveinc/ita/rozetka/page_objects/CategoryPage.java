package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.title;
import static java.lang.String.format;

public class CategoryPage extends BasePage {

    @Step("Category page: open product category page for category {categoryName}")
    public CategoryPage openProductCategoryPage(String categoryName) {
        $x(format("//a[@class='tile-cats__heading tile-cats__heading_type_center'" +
                " and contains(text(), '%s')]", categoryName)).click();
        return new CategoryPage();
    }

    @Step("Category page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//span[@class='goods-tile__title'])[%d]", number)).click();
        return new ProductPage();
    }

    @Step("Category page: open products list page for category index {index}")
    public ProductsListPage openProductsListPage(int index) {
        $x(format("(//a[@class='tile-cats__heading tile-cats__heading_type_center'])[%d]", index)).click();
        return new ProductsListPage();
    }

    @Step("Category page: open products list page for category {productCategoryName}")
    public ProductsListPage openProductsListPage(String productCategoryName) {
        $x(format("//a[contains(@class, 'tile-cats__heading') and contains(text(), '%s')]", productCategoryName)).click();
        return new ProductsListPage();
    }

    public String getCategoryTitle() {
        return $x("//h1[@class='portal__heading']").text();
    }

    public String getPageTitle() {
        return title();
    }

    public int getCategoriesAmount() {
        return $$x("//li[@class='portal-grid__cell']")
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1))
                .size();
    }
    @Step("Category page: open products list page with subcategories by category number {number}")
    public ProductsListPageWithSubcategories openProductsListPageWithSubcategories(int number) {
        $x(format("(//li[contains(@class, 'portal-grid__cell')])[%d]", number)).click();
        return new ProductsListPageWithSubcategories();
    }
}

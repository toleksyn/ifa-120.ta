package com.softserveinc.ita.rozetka.modules;

import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import com.softserveinc.ita.rozetka.page_objects.ProductsListPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CatalogMenu {

    @Step("Catalog menu: open category by name {categoryName}")
    public CategoryPage openCategory(String categoryName) {
        $x(format("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName)).click();
        // click on microphone element to hide dropdown
        $x("//button[contains(@class, 'search-form__microphone')]").click();
        return new CategoryPage();
    }

    public List<String> getCategoryNames() {
        return $$x("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }

    @Step("Catalog menu: scroll to category by category name {categoryName}")
    public CatalogMenu scrollToCategory(String categoryName) {
        var category = $x(format("//ul[contains(@class, 'menu-categories')] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName));
        actions().moveToElement(category).perform();
        return this;
    }

    public int getCategoriesAmount() {
        return $$x("//li//a[@class='menu__hidden-title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    @Step("Catalog menu: open products list page by category name {categoryName}")
    public ProductsListPage openProductsListPage(String productCategoryName) {
        $x(format("//li//a[@class='menu__hidden-title' and contains(text(), '%s')]", productCategoryName)).click();
        return new ProductsListPage();
    }
}

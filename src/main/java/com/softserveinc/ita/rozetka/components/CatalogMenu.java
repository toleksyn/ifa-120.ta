package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import com.softserveinc.ita.rozetka.page_objects.ProductsListPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
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

    @Step("CatalogMenu: open products list {subCategoryName} in section {subCategorySectionName} through hovered category {hoveredCategoryName}")
    public ProductsListPage openProductsListBySubCategory(String hoveredCategoryName, String subCategorySectionName, String subCategoryName) {
        $x(format("//ul[contains(@class, 'menu-categories_type_main')]//a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", hoveredCategoryName)).hover();
        $x(format("//a[contains(text(),'%s')]/following-sibling::ul//a[contains(text(), '%s')]", subCategorySectionName, subCategoryName)).click();
        return new ProductsListPage();
    }
}

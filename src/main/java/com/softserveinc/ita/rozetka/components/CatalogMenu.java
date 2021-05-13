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

    @Step("CatalogMenu: open category by name {categoryName}")
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

    @Step("CatalogMenu: open products list {subCategoryName} through hovered category {hoveredCategoryName}")
    public ProductsListPage openProductsListByCategoryInCatalogMenu(String hoveredCategoryName, String... subCategoryName) {
        $x(format("//ul[contains(@class, 'menu-categories_type_main')]//a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", hoveredCategoryName)).hover();
        var countSubCategoriesInChain = subCategoryName.length;
        switch (countSubCategoriesInChain) {
            case 0:
                $x(format("//ul[contains(@class, 'menu-categories_type_main')]//a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", hoveredCategoryName)).click();
                break;
            case 1:
                $x(format("//a[contains(text(),'%s')]", subCategoryName[0])).click();
                break;
            case 2:
                $x(format("//a[contains(text(),'%s')]/following-sibling::ul//a[contains(text(), '%s')]", subCategoryName[0], subCategoryName[1])).click();
        }
        return new ProductsListPage();
    }
}

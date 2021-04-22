package com.softserveinc.ita.rozetka.page_objects;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CatalogMenu {
    public CategoryPage openCategoryPage(String categoryName) {
        $x(format("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName)).click();
        // click on microphone element to hide dropdown
        $x("//button[@class='search-form__microphone']").click();
        return new CategoryPage();
    }

    public CatalogMenu scrollToCategory(String categoryName) {
        var category = $x(format("//ul[@class='menu-categories'] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName));
        actions().moveToElement(category).perform();
        return this;
    }

    public int getCategoriesSize() {
        return $$x("//li//a[@class='menu__hidden-title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    public ProductsListPage openProductsListPage(String productCategoryName) {
        $x(format("//li//a[@class='menu__hidden-title' and contains(text(), '%s')]", productCategoryName)).click();
        return new ProductsListPage();
    }

    public List<String> getCategoryNames() {
        return $$x("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
}

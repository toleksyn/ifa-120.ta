package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class SubcategoryPage {
    @Step("Subcategory page: open products list")
    public ProductsListPage openProductsListPage(String productsName) {
        $x(format("//span[(@class='portal-navigation__link-text') and contains(text(), '%s')]", productsName)).click();
        return new ProductsListPage();
    }
}

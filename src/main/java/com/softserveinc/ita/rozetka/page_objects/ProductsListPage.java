package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.enums.SortingOption;
import com.softserveinc.ita.rozetka.utility_class.AgeConfirmationPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class ProductsListPage extends BasePage {

    public int getProductsAmount() {
        return $$x("//span[@class='goods-tile__title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    @Step("Products list page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//a[contains(@class, 'goods-tile__picture')])[%d]", number)).click();
        return new ProductPage();
    }

    @Step("Products list page: set sorting type {sortingOption}")
    public ProductsListPage setSortingType(SortingOption sortingOption) {
        $x("//select").selectOptionByValue(sortingOption.getSortingOptionValue());
        return this;
    }

    public String getProductName(int number) {
        return $x(format("(//span[@class='goods-tile__title'])[%d]", number)).text();
    }

    public int getPriceFromProduct(int number) {
        return parseInt($x(format("(//span[@class='goods-tile__price-value'])[%d]", number))
                .text()
                .replaceAll(" ", ""));
    }

    @Step("Products list page: open next page")
    public ProductsListPage openNextPage() {
        $x("//a[contains(@class, 'pagination__direction_type_forward')]").click();
        return this;
    }

    @Step("Products list page: open previous page")
    public ProductsListPage openPreviousPage() {
        $x("//a[contains(@class, 'pagination__direction')]").click();
        return this;
    }

    public int getCurrentPageNumber() {
        return parseInt($x("//a[contains(@class, 'pagination__link_state_active')]").text());
    }

    @Step("Products list page: filter products list by filter name {filterName}")
    public ProductsListPage filterProductsList(String filterName) {
        $x(format("//label[contains(text(),'%s')]", filterName)).click();
        return new ProductsListPage();
    }

    @Step("Products list page: open product by name {productName}")
    public ProductPage openProductByName(String productName) {
        $x(format("//span[@class='goods-tile__title'and contains(text(), '%s')]", productName)).click();
        return new ProductPage();
    }

    public String getPageTitle() {
        return $x("//h1[@class='catalog-heading']").text();
    }

    @Step("Products list page: show more products")
    public ProductsListPage showMoreProducts() {
        $x("//a[@class='show-more show-more--horizontal']").click();
        return this;
    }

    @Step("Products list page: get age confirmation popup")
    public AgeConfirmationPopup getAgeConfirmationPopup() {
        return new AgeConfirmationPopup();
    }
}

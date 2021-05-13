package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.AgeConfirmationPopup;
import com.softserveinc.ita.rozetka.enums.SortingOption;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class ProductsListPage extends BasePage {

    public int getProductsAmount() {
        return $$x("//span[@class='goods-tile__title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    @Step("Products list page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//a[contains(@class, 'goods-tile__heading')])[%d]", number)).click();
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
        return $x("//h1[contains(@class, 'catalog-heading')]").text();
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

    @Step("Products list page: set lowest price {price}")
    public ProductsListPage setLowestPrice(int price) {
        $x("//input[contains(@formcontrolname, 'min')]").val(valueOf(price));
        return this;
    }

    @Step("Products list page: set highest price {price}")
    public ProductsListPage setHighestPrice(int price) {
        $x("//input[contains(@formcontrolname, 'max')]").val(valueOf(price));
        return this;
    }

    @Step("Products list page: confirm filter price range")
    public ProductsListPage confirmFilterPriceRange() {
        $x("//button[contains(@class, 'slider-filter__button')]").click();
        return this;
    }

    @Step("Products list page: set check box filter {filterValue} in section {filterSection}")
    public ProductsListPage setCheckBoxFilter(String filterSection, String filterValue) {
        $x(format("//button[span[contains(text(), '%s')]]/following-sibling::div//label[contains(text(), '%s')]", filterSection, filterValue)).click();
        actions()      //  manipulations to display the item in the case of its invisibility
                .sendKeys(Keys.HOME)
                .perform();
        return this;
    }
}

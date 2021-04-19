package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class ProductsListPage extends BasePage {

    public int getProductListSize() {
        return $$x("//span[@class='goods-tile__title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    public ProductPage openProductByNumber(int number) {
        $x(format("(//a[@class='goods-tile__picture'])[%d]", number)).click();
        return new ProductPage();
    }

    public ProductsListPage setSortingType(String sortingTypeKey) {
        $x("//select").selectOptionContainingText(sortingTypeKey);
        return this;
    }

    public String getProductNameForProduct(int number) {
        return $x(format("(//span[@class='goods-tile__title'])[%d]", number)).text();
    }

    public int getProductPriceForProduct(int number) {
        return parseInt($x(format("(//span[@class='goods-tile__price-value'])[%d]", number))
                .text()
                .replaceAll(" ", ""));
    }

    public ProductsListPage openNextPage() {
        $x("//a[@class='button button_color_gray button_size_medium" +
                " pagination__direction pagination__direction_type_forward']").click();
        return this;
    }

    public ProductsListPage openPreviousPage() {
        $x("//a[@class='button button_color_gray button_size_medium" +
                " pagination__direction']").click();
        return this;
    }

    public int getCurrentPageNumber() {
        return parseInt($x("//a[@class='pagination__link pagination__link_state_active']").text());
    }

    public ProductsListPage filterProductsList(String filterName) {
        $x(format("//label[contains(text(),'%s')]", filterName)).click();
        return this;
    }

    public ProductPage openProductByName(String productName) {
        $x(format("//span[@class='goods-tile__title'and contains(text(), '%s')]", productName)).click();
        return new ProductPage();
    }

    public AgeConfirmationBanner getAgeConfirmation() {
        return new AgeConfirmationBanner();
    }
}

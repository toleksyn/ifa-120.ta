package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsListPage extends BasePage {
    public ElementsCollection getProductList() {
        return Selenide.$$x("//span[@class='goods-tile__title']");
    }

    public ProductPage openProductByNumber(int number) {
        $x(String.format("(//a[@class='goods-tile__picture'])[%d]", number)).click();
        return new ProductPage();
    }

    public ProductsListPage setSortingType(String sortingTypeKey) {
        $x("//select").selectOptionContainingText(sortingTypeKey);
        return this;
    }

    public String getProductNameByNumber(int number) {
        return $x(String.format("(//span[@class='goods-tile__title'])[%d]", number)).text();
    }

    public int getProductPriseByNumber(int number) {
        return Integer.parseInt($x(String.format("(//span[@class='goods-tile__price-value'])[%d]", number)).text().replaceAll(" ", ""));
    }

    public ProductsListPage openNextResultPage() {
        $x("//a[@class='button button_color_gray button_size_medium pagination__direction pagination__direction_type_forward']").click();
        return this;
    }

    public ProductsListPage openPreviousResultPage() {
        $x("//a[@class='button button_color_gray button_size_medium pagination__direction']").click();
        return this;
    }

    public int getCurrentPageNumber() {
        return Integer.parseInt($x("//a[@class='pagination__link pagination__link_state_active']").text());
    }

    public ProductsListPage useFilter(String filterName) {
        $x(String.format("//label[contains(text(),'%s')]", filterName)).click();
        return this;
    }

    public ProductPage openProductByName(String productName) {
        $x(String.format("//span[@class='goods-tile__title'and contains(text(), '%s')]", productName)).click();
        return new ProductPage();
    }
}

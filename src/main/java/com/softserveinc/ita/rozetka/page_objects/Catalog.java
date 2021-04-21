package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class Catalog {

    public Catalog scrollToCategory(String categoryName) {
        SelenideElement category = $x(format("//ul[@class='menu-categories'] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName));
        actions().moveToElement(category).perform();
        return this;
    }

    public int getCategoryListSize() {
        return $$x("//li//a[@class='menu__hidden-title']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .size();
    }

    public ProductsListPage openProductsListPage(String productCategoryName) {
        $x(format("//li//a[@class='menu__hidden-title' and contains(text(), '%s')]", productCategoryName)).click();
        return new ProductsListPage();
    }

}

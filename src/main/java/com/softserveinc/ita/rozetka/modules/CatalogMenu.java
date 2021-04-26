package com.softserveinc.ita.rozetka.modules;

import com.softserveinc.ita.rozetka.page_objects.CategoryPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CatalogMenu {

    @Step("Left side bar: open category by name {categoryName}")
    public CategoryPage openCategory(String categoryName) {
        $x(format("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link') and contains(text(), '%s')]", categoryName)).click();
        // click on microphone element to hide dropdown
        $x("//button[@class='search-form__microphone']").click();
        return new CategoryPage();
    }

    public List<String> getCategoryNames() {
        return $$x("//ul[contains(@class, 'menu-categories_type_main')] //a[contains(@class, 'menu-categories__link')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
}

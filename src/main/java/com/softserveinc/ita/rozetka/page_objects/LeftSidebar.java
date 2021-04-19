package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class LeftSidebar {

    public CategoryPage openCategory(String categoryName) {
        $x(format("//a[@class = 'menu-categories__link' and contains(text(), '%s')]", categoryName)).click();
        // click on microphone element to hide dropdown
        $x("//button[@class='search-form__microphone']").click();
        return new CategoryPage();
    }
}
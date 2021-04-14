package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class LeftSidebar {

    public CategoryPage openCategory(String categoryName){
        $x(String.format("//a[@class = 'menu-categories__link' and contains(text(), '%s')]", categoryName))
                .click();
        $x("//button[@class='search-form__microphone']")
                .click();
        return new CategoryPage();
    }
}

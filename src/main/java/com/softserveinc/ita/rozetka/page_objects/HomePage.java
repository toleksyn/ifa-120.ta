package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage openCategoryPage(int number) {
        $$x("//a[@class = 'menu-categories__link']").get(number).click();
        return new CategoryPage();
    }

    public CategoryPage openCategoryPageFromLeftSidebar(int number) {
        $x(String.format("(//ul[@class='menu-categories menu-categories_type_main']//li[%d])", number)).doubleClick();
        return new CategoryPage();
    }

    public CategoryPage openCategoryPageFromLeftSidebar(String pageCategory) {
        $x(String.format("//a[@class = 'menu-categories__link' and contains(text(), '%s')]", pageCategory))
                .click();
        $x("//button[@class='search-form__microphone']")
                .click();
        return new CategoryPage();
    }
}

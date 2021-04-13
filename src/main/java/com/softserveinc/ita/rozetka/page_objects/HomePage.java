package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;

public class HomePage {

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage openCategoryPage(int number) {
        Selenide.$$x("//a[@class = 'menu-categories__link']").get(number).click();
        return new CategoryPage();
    }

}

package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage {

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage navigateToCategoryPage(int number) {
        Selenide.$$x("//a[@class = 'menu-categories__link']").get(number).click();
        return new CategoryPage();
    }

    public CategoryPage navigateToCategoryPageFromLeftSidebar(int number) {
        $x(String.format("(//ul[@class='menu-categories menu-categories_type_main']//li[%d])", number)).doubleClick();
        return new CategoryPage();
    }
}

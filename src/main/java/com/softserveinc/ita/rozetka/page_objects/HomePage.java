package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    private LeftSidebar leftSidebar;

    public HomePage() {
        leftSidebar = new LeftSidebar();
    }

    public LeftSidebar getLeftSidebar() {
        return leftSidebar;
    }

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage openCategoryPage(int number) {
        $$x("//a[@class = 'menu-categories__link']").get(number).click();
        return new CategoryPage();
    }

    public ProductPage openProduct(int index) {
        $x(String.format("//li[@class='main-goods__cell'][%d]", index)).click();
        return new ProductPage();
    }
}

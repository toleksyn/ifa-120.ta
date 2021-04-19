package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    private final LeftSidebar leftSidebar = new LeftSidebar();

    public LeftSidebar getLeftSidebar() {
        return leftSidebar;
    }

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[@class='main-goods__cell'])[%d]", number)).click();
        return new ProductPage();
    }
}

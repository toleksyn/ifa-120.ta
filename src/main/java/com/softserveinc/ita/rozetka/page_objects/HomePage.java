package com.softserveinc.ita.rozetka.page_objects;

import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class HomePage extends BasePage {

    private final LeftSidebar leftSidebar = new LeftSidebar();

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[@class='main-goods__cell'])[%d]", number)).click();
        return new ProductPage();
    }

    public BasketPage openBasketPage() {
        $x("//use[@href='#icon-header-basket']").click();
        return new BasketPage();
    }
}

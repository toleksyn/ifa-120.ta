package com.softserveinc.ita.rozetka.page_objects;

import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
    private final CatalogMenu catalogMenu = new CatalogMenu();
    private final Header header = new Header();

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[@class='main-goods__cell'])[%d]", number)).click();
        return new ProductPage();
    }

    public BasketPage openBasketPage() {
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }
}

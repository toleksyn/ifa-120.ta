package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
    private final LeftSidebar leftSidebar = new LeftSidebar();

    @Step("Home page: open home page")
    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    @Step("Home page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[@class='main-goods__cell'])[%d]", number)).click();
        return new ProductPage();
    }

    @Step("Home page: open basket page")
    public BasketPage openBasketPage() {
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }

    public String getViewedProductName(int number) {
        return $$x("//a[@class='tile__title']")
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1))
                .get(number)
                .text();
    }
}

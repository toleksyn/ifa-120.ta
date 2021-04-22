package com.softserveinc.ita.rozetka.page_objects;

import lombok.Getter;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
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
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }

    public int getHomePageProductsListSize(int minProductsListSize) {
        actions().sendKeys(Keys.PAGE_DOWN)   // page scrolling to dynamically increase the list of products
                .sendKeys(Keys.END)
                .perform();
        return $$x("//li[@class='main-goods__cell']")
                .shouldHave(sizeGreaterThan(minProductsListSize))
                .size();
    }

    public String getProductNameByNumber(int number) {
        return $x(format("(//a[@class='tile__title'])[%d]", number)).text();
    }
}

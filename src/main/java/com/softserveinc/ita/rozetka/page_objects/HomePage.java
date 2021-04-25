package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
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

    @Step("Home page: open Basket Page")
    public BasketPage openBasketPage() {
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }

    public String getProductNameByNumber(int number) {
        return $x(format("(//a[@class='tile__title'])[%d]", number)).text();
    }

    public HomePage getOnlyOnRozetkaProductsSection() {
        actions().sendKeys(Keys.PAGE_DOWN)   // page scrolling to dynamically increase the list of products
                .perform();
        $x("//*[contains(text(),'Тільки в Розетці')]/following-sibling::button[contains(@class,'main-goods__show-more')]")
                .click();
        return this;
    }

    public int getProductsCount() {
        return $$x("//*[contains(text(),'Тільки в Розетці')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li")
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    @Step("Home page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        actions().sendKeys(Keys.DOWN)   //manipulations to display the item, in the case of overlapping by advertising pop-up window
                .moveToElement($x(format("//*[contains(text(),'Тільки в Розетці')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li[contains(@class,'main-goods__cell')][%d]", number)))
                .click()
                .perform();
        return new ProductPage();
    }

}

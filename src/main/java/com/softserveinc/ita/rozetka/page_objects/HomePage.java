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

    @Step("Home page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[@class='main-goods__cell'])[%d]", number)).click();
        return new ProductPage();
    }

    @Step("Home page: open Basket Page")
    public BasketPage openBasketPage() {
        $x("//button[@class='header__button header__button--active']").click();
        return new BasketPage();
    }

    public String getProductNameByNumber(int number) {
        return $x(format("(//a[@class='tile__title'])[%d]", number)).text();
    }


    public HomePage getProductsSectionByName(String sectionName) {
        actions()
                .sendKeys(Keys.PAGE_DOWN)   // page scrolling to dynamically increase the list of products
                .sendKeys(Keys.END)
                .perform();
        $x(format("//*[contains(text(), '%s')]/following-sibling::button[contains(@class,'main-goods__show-more')]", sectionName))
                .click();
        return this;
    }

    public int getProductsCount(String sectionName) {
        return $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li",
                sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    @Step("Home page: open product by number in section {number}")
    public ProductPage openProductByNumberInSection(int number, String sectionName) {
        actions()   //manipulations to display the item, in the case of overlapping by advertising pop-up window
                .sendKeys(Keys.PAGE_DOWN)
                .moveToElement($x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li[contains(@class,'main-goods__cell')])[%d]",
                        sectionName, number)))
                .click()
                .perform();
        return new ProductPage();
    }

    public String getProductNameByNumberInSection(int number, String sectionName) {
        return $x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li//*[contains(@class,'tile__title')])[%d]",
                sectionName, number))
                .text();
    }
}

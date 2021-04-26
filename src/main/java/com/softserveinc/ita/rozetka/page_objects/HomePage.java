package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.CollectionCondition;
import com.softserveinc.ita.rozetka.modules.CatalogMenu;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
    private final CatalogMenu catalogMenu = new CatalogMenu();

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    @Step("Home page: open product by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[contains(@class,'main-goods__cell')])[%d]", number)).click();
        return new ProductPage();
    }

    public String getViewedProductName(int number) {
        return $$x("//a[@class='tile__title']")
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1))
                .get(number)
                .text();
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

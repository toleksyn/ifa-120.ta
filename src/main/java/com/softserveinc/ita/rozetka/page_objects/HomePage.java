package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.components.ProductsSection;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
    private final CatalogMenu catalogMenu = new CatalogMenu();

    @Step("Home page: open home page")
    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

    @Step("Home page: open product by number for number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[contains(@class,'main-goods__cell')])[%d]", number)).click();
        return new ProductPage();
    }

    public String getViewedProductName(int number) {
        return $x(format("(//a[contains(@class,'tile__picture')])[%d]", number)).text();
    }

    public String getProductNameByNumber(int number) {
        return $x(format("(//a[@class='tile__title'])[%d]", number)).text();
    }

    @Step("Home page: open product section by name for section: {sectionName}")
    public ProductsSection openProductsSection(String sectionName) {
        actions()
                .sendKeys(Keys.PAGE_DOWN)   // page scrolling to dynamically increase the list of products
                .sendKeys(Keys.END)
                .perform();
        $x(format("//*[contains(text(), '%s')]/following-sibling::button[contains(@class,'main-goods__show-more')]", sectionName)).click();
        return new ProductsSection(sectionName);
    }
}

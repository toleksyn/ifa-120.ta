package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.CatalogMenu;
import com.softserveinc.ita.rozetka.components.ProductsSection;
import com.softserveinc.ita.rozetka.enums.ProductSection;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;

import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class HomePage extends BasePage {

    @Getter
    private final CatalogMenu catalogMenu = new CatalogMenu();

    @Step("Home page: open home page")
    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        closePopupAdWindow();   //closes the pop-up ad window if it appears, to avoid overlapping the product item
        return this;
    }

    @Step("Home page: open product by number {number}")
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
    public ProductsSection openProductsSection(ProductSection sectionName) {
        closePopupAdWindow();   //closes the pop-up ad window if it appears, to avoid overlapping the product item
        IntStream.range(0, 18).forEach(scrollsCount -> actions()   // page scrolling to dynamically increase the count of products sections
                .sendKeys(Keys.END)
                .perform());
        $x(format("//*[contains(text(), '%s')]/following-sibling::button[contains(@class,'main-goods__show-more')]", sectionName.getSectionName())).click();
        return new ProductsSection(sectionName.getSectionName());
    }

    public void closePopupAdWindow() {
        if ($x("//*[contains(@id,'rz-banner')]").exists()) {
            $x("//*[contains(@id,'rz-banner')]/span").click();
        }
    }
}

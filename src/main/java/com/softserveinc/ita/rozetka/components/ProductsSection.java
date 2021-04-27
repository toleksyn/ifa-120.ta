package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class ProductsSection {

    public int getProductsCount(String sectionName) {
        return $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li", sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    @Step("Products section page: open product by number {number} in section {sectionName}")
    public ProductPage openProductByNumber(int number, String sectionName) {
        actions()   //manipulations to display the item, in the case of overlapping by advertising pop-up window
                .sendKeys(Keys.PAGE_DOWN)
                .moveToElement($x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li[contains(@class,'main-goods__cell')])[%d]", sectionName, number)))
                .click()
                .perform();
        return new ProductPage();
    }

    public String getProductNameByNumber(int number, String sectionName) {
        return $x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li//*[contains(@class,'tile__title')])[%d]", sectionName, number)).text();
    }
}

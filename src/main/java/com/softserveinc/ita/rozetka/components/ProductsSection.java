package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class ProductsSection {

    private final String sectionName;

    public ProductsSection(String sectionName) {
        this.sectionName = sectionName;
    }

    int randomNumber;

    public int randomProductNumber() {
        var productsCount = $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li", this.sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
        randomNumber = Math.max((int) ((Math.random() * 1000 * productsCount) / 1000), 1);
        return randomNumber;
    }

    public int getProductsCount() {
        return $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li", this.sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    public String getRandomProductName() {
        randomNumber = randomProductNumber();
        return $x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li//*[contains(@class,'tile__title')])[%d]", this.sectionName, randomNumber)).text();
    }

    @Step("Products section page: open product random number:{number} in section: {sectionName}")
    public ProductPage openRandomProduct() {
        actions()   //manipulations to display the item, in the case of overlapping by advertising pop-up window
                .sendKeys(Keys.PAGE_DOWN)
                .moveToElement($x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li[contains(@class,'main-goods__cell')])[%d]", this.sectionName, randomNumber)))
                .click()
                .perform();
        return new ProductPage();
    }
}

package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import io.qameta.allure.Step;
import lombok.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

@AllArgsConstructor
public class ProductsSection {
    private final String sectionName;

    public int getProductsCountInSection() {
        return $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li", this.sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    public int getProductsCount() {
        return $$x(format("//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li", this.sectionName))
                .shouldHave(sizeGreaterThan(6))
                .size();
    }

    public String getProductNameAtPosition(int productPosition) {
        return $x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li//*[contains(@class,'tile__title')])[%d]", this.sectionName, productPosition)).text();
    }

    @Step("Products section page: open product at position: {productPosition} in section: {this.sectionName}")
    public ProductPage openProductAtPosition(int productPosition) {
        actions()
                .sendKeys(Keys.PAGE_DOWN)   //manipulations to display the item, in the case of overlapping by advertising pop-up window
                .moveToElement($x(format("(//*[contains(text(), '%s')]/following-sibling::ul[contains(@class,'main-goods__grid')]/li[contains(@class,'main-goods__cell')])[%d]", this.sectionName, productPosition)))
                .click()
                .perform();
        return new ProductPage();
    }
}

package com.softserveinc.ita.rozetka.modules;

import com.softserveinc.ita.rozetka.page_objects.ProductPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class CheaperTogetherMenu extends ProductPage {

    public String getProductNameByNumber(int number) {
        return $x(format("(//li[contains(@class, 'kits-list__item_type_proposed')]//a[contains(@class, 'kits-tile__title')])[%d]", number)).text();
    }

    public Integer getDiscountPrice(int number) {
        var discountPriceText = $x(format("(//div[contains(@class, 'ng-star-inserted')]//p[contains(@class, 'kits-tile__price_color_red ')])[%d]", number)).text();
        var discountPrice = parseInt(discountPriceText
                .substring(0, discountPriceText.length() - 1)
                .replaceAll(" ", ""));
        return discountPrice;
    }

    @Step("Cheaper together menu: open product page by number {number}")
    public ProductPage openProductByNumber(int number) {
        $x(format("(//li[contains(@class, 'kits-list__item_type_proposed')]//a[contains(@class, 'kits-tile__title')])[%d]", number)).click();
        return new ProductPage();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HelpCenterPage {

    public String getHeaderText() {
        return $x("//header[contains(@class, 'header')]").text();
    }

    public List<String> getHelpCategoryList() {
        return $$x("//p[contains(@class, 'main-category__title')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }

    @Step("Help center page: open payment help category page")
    public PaymentHelpCategoryPage openPaymentHelpCategoryPage() {
        $x("//p[@id='title_payment']//a").click();
        return new PaymentHelpCategoryPage();
    }
}

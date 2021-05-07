package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;

public class ComparisonPage extends BasePage {

    public List<String> getCharacteristicForProduct(int number) {
        var allCharacteristics = $$x("//dd[@class='comparison-characteristic__value']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
        var firstProductCharacteristics = new ArrayList<String>();
        var secondProductCharacteristics = new ArrayList<String>();
        range(0, allCharacteristics.size())
                .forEach(index -> (index % 2 == 0 ? firstProductCharacteristics : secondProductCharacteristics)
                        .add(allCharacteristics.get(index)));
        switch (number) {
            case 1:
                return firstProductCharacteristics;
            case 2:
                return secondProductCharacteristics;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Step("Comparison page: show differences")
    public ComparisonPage showDifferences() {
        $x("//button[contains(@class, 'comparison-settings__toggle')]").click();
        return this;
    }

    public ComparisonPage deleteProduct(int productNumber) {
        $x(format("(//button[@class='button button--white button--small context-menu__toggle'])[%d]", productNumber)).click();
        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
        return this;
    }

    public String getAlertMessage() {
        return $x("//div[@class='form__hint form__hint_type_attention ng-star-inserted']").text();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.iterate;

public class ComparisonPage extends BasePage {

    public List<String> getCharacteristicForProduct(int number) {
        var allCharacteristics = $$x("//dd[@class='comparison-characteristic__value']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();

        IntStream stream = null;
        if (number == 1) {
            stream = iterate(0, index -> index < allCharacteristics.size(), index -> index + 2);
        } else if (number == 2) {
            stream = iterate(1, index -> index < allCharacteristics.size(), index -> index + 2);
        }
        if (number == 1 || number == 2) {
            return stream
                    .mapToObj(allCharacteristics::get)
                    .collect(toCollection(ArrayList::new));
        } throw new IllegalArgumentException("Incorrect number of product");
    }

    @Step("Comparison page: show differences")
    public ComparisonPage showDifferences() {
        $x("//button[contains(@class, 'comparison-settings__toggle')]").click();
        return this;
    }
}

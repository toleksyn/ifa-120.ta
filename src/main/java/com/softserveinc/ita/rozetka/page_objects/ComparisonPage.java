package com.softserveinc.ita.rozetka.page_objects;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

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

        switch (number) {
            case 1:
                return iterate(0, index -> index < allCharacteristics.size(), index -> index + 2)
                        .mapToObj(allCharacteristics::get)
                        .collect(toCollection(ArrayList::new));
            case 2:
                return iterate(1, index -> index < allCharacteristics.size(), index -> index + 2)
                        .mapToObj(allCharacteristics::get)
                        .collect(toCollection(ArrayList::new));
            default:
                throw new IllegalArgumentException();
        }
    }

    @Step("Comparison page: show differences")
    public ComparisonPage showDifferences() {
        $x("//button[contains(@class, 'comparison-settings__toggle')]").click();
        return this;
    }
}

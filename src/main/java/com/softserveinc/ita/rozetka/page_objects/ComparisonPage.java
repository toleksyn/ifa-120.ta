package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage extends BasePage {

    public List<String> getCharacteristicList() {
        return $$x("//dd[@class='comparison-characteristic__value']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }

    @Step("Comparison page: open different characteristics")
    public ComparisonPage openDifferentCharacteristics() {
        $x("//button[contains(@class, 'comparison-settings__toggle')]").click();
        return this;
    }
}

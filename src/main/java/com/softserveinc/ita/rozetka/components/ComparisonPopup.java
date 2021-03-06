package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ComparisonPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ComparisonPopup {

    @Step("Comparison popup: open comparison page from list {number}")
    public ComparisonPage openComparisonPageFromList(int number) {
        $x(format("(//a[contains(@class, 'comparison-modal__link')])[%s]", number)).click();
        return new ComparisonPage();
    }

    public String getNameFromList(int number) {
        return $x(format("(//a[contains(@class, 'comparison-modal__link')])[%d]", number)).text();
    }

    @Step("Comparison popup: delete comparison list by number {number}")
    public ComparisonPopup deleteComparisonList(int number) {
        $x(format("(//button[contains(@class, 'comparison-modal__remove')])[%d]", number)).click();
        return this;
    }
}

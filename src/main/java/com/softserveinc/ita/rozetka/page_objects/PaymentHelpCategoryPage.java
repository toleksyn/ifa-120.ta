package com.softserveinc.ita.rozetka.page_objects;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;

public class PaymentHelpCategoryPage {

    public List<String> getSectionsTitleList() {
        return $$x("//a[contains(@class, 'section-list-link')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
}

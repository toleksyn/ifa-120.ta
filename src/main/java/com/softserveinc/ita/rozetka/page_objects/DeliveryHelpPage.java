package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.components.Footer;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class DeliveryHelpPage {

    public String getHeaderText() {
        return $x("//h1").text();
    }

    public List<String> getHelpItemsNamesList() {
        return $$x("//a[contains(@class, 'sidenav-item')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
}

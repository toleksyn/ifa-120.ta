package com.softserveinc.ita.rozetka.page_objects;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ContactsPage extends BasePage {

    public String getPageTitle() {
        return $x("//h1[@class='static-page__heading']").text();
    }

    public List<String> getPhoneNumbers() {
        return $$x("//a[@class='contacts-main__number']")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
}
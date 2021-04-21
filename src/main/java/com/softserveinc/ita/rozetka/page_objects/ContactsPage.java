package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class ContactsPage extends BasePage {
    public String getPageTitle() {
        return $x("//h1[@class='static-page__heading']").text();
    }

    public boolean isContactsSectionDisplayed() {
        return $x("(//section[@class='contacts-section'])[1]").isDisplayed();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.Footer;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ContactsPage extends BasePage {

    public String getPageTitle() {
        return $x("//h1[contains(@class, 'static-page__heading')]").text();
    }

    public List<String> getPhoneNumbers() {
        return $$x("//a[contains(@class, 'contacts-main__number')]")
                .shouldHave(sizeGreaterThanOrEqual(1))
                .texts();
    }
    @Step("ContactsPage: open Footer")
    public Footer openFooter() {
        $x("//img[contains(@alt, 'Rozetka Logo')]").click();
        return new Footer();
    }

}

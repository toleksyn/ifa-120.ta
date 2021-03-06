package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.enums.LanguageOption;
import com.softserveinc.ita.rozetka.page_objects.ContactsPage;
import com.softserveinc.ita.rozetka.page_objects.HelpCenterPage;
import com.softserveinc.ita.rozetka.page_objects.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class HamburgerBar {

    @Step("Hamburger bar: switch language to {language}")
    public HomePage switchLanguage(LanguageOption language) {
        $x(format("//div[contains(@class, 'side-menu__switch')]//a[contains(text(), '%s')]", language.getLanguageKey()))
                .click();
        return new HomePage();
    }

    @Step("Hamburger bar: open contacts page")
    public ContactsPage openContactsPage() {
        $x("//a[contains(@class, 'ng-tns-c19-2') and contains(@href, 'contacts')]").click();
        return new ContactsPage();
    }

    @Step("Hamburger bar: open help center page")
    public HelpCenterPage openHelpCenterPage() {
        $x("//a[contains(@class, 'side-menu__button') and contains(@href, 'help')]").click();
        return new HelpCenterPage();
    }

    public String getSideMenuAuthText() {
        return $x("//*[contains(@class, 'side-menu__auth')]").text();
    }

    @Step("Hamburger bar: close hamburger bar")
    public HamburgerBar closeBar() {
        $x("//button[contains(@class, 'side-menu__close')]").click();
        return this;
    }
}

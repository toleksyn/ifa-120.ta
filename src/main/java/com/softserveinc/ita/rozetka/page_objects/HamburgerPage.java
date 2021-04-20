package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class HamburgerPage {

    public HomePage changeLanguage() {
        $x("(//a[@class='lang__link'])[2]").click();
        return new HomePage();
    }

    public ContactsPage openContactsPage() {
        $x("(//a[@class='button button--large side-menu__button'])[3]").click();
        return new ContactsPage();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class HamburgerPage {

    public HomePage switchLanguage() {
        $x("//div[@class='side-menu__switch']//a[@class='lang__link']").click();
        return new HomePage();
    }

    public ContactsPage openContactsPage() {
        $x("(//a[contains(@class, 'side-menu__button')])[3]").click();
        return new ContactsPage();
    }
}

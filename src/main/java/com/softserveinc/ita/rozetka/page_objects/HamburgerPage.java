package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class HamburgerPage {

    public HomePage changeLanguage(String language) {
        $x(format("(//a[@class='lang__link' and contains(text(), '%s')])[2]", language)).click();
        return new HomePage();
    }

    public ContactsPage openContactsPage() {
        $x("(//a[contains(@class, 'side-menu__button')])[3]").click();
        return new ContactsPage();
    }
}

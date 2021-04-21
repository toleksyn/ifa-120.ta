package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class HamburgerBar {

    public HomePage switchLanguage(LanguageOption language) {
        $x(format("//div[@class='side-menu__switch']//a[contains(text(), '%s')]", language.getLanguageKey())).click();
        return new HomePage();
    }

    public ContactsPage openContactsPage() {
        $x("//a[contains(@class, 'side-menu__button') and contains(@href, 'contacts')]").click();
        return new ContactsPage();
    }
}

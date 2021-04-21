package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class HamburgerBar {

    public HomePage changeLanguage(String language) {
        $x(format("//a[@class='lang__link' and contains(text(), '%s')]", language)).click();
        return new HomePage();
    }
}

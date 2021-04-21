package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class HamburgerBar {

    public HomePage switchLanguage() {
        $x("//div[@class='side-menu__switch']//a[@class='lang__link']").click();
        return new HomePage();
    }
}

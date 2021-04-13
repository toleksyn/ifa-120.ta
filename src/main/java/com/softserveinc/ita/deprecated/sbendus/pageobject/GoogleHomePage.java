package com.softserveinc.ita.deprecated.sbendus.pageobject;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    public GoogleHomePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchPage searchFor(String searchTerm) {
        $x("//input[@class='gLFyf gsfi']").val(searchTerm).pressEnter();
        return new GoogleSearchPage();
    }
}

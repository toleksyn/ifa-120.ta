package com.softserveinc.ita.deprecated.nromanchuk;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GoogleHomePage {

    public GoogleHomePage openGoogleHomePage() {
        open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        $x("//input[@name='q']").setValue(searchTerm).pressEnter();
        return new GoogleSearchResultPage();
    }
}
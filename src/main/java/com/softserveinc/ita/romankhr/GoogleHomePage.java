package com.softserveinc.ita.romankhr;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    public GoogleHomePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        $x("//input[@class='gLFyf gsfi']").setValue(searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

package com.softserveinc.ita.sbendus.pageobject;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    public GoogleHomePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchPage searchFor(String searchTerm) {
        $x("//input[@class='gLFyf gsfi']").sendKeys(searchTerm + Keys.ENTER);
        return new GoogleSearchPage();
    }
}

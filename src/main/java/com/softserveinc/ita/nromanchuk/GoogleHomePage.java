package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GoogleHomePage {

    public GoogleHomePage openGoogleHomePage() {
        open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        $x("//input[@name='q']").setValue(searchTerm).sendKeys(Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}
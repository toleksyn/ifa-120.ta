package com.softserveinc.ita.ynamurovanyi;

import com.codeborne.selenide.Selenide;

public class GoogleHomePage {

    public GoogleHomePage open() {
        Selenide.open("https://google.com");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String searchTerm) {
        Selenide.$x("//input[@class='gLFyf gsfi']").val(searchTerm).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public String getTitle() {
        return Selenide.title();
    }
}

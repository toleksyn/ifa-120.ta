package com.softserveinc.ita.deprecated.vpetrat;

import com.codeborne.selenide.Selenide;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String request) {
        Selenide.$x("//input[@class='gLFyf gsfi']").val(request).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public GoogleLuckySearchResultPage luckySearchFor(String request) {
        Selenide.$x("//input[@class='gLFyf gsfi']").val(request);
        Selenide.$x("//input[@name='btnI']").click();
        return new GoogleLuckySearchResultPage();
    }

    public String getPageTittle() {
        return Selenide.title();
    }
}

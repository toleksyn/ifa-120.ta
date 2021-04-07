package com.softserveinc.ita.vpetrat;


import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {
    public List<String> getListOfSearchResultLinksText() {
        return Selenide.$$x("//h3[@class='LC20lb DKV0Md']")
                .texts();
    }

    public List<String> getListOfSearchResultLinks() {
        return Selenide.$$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(selenideElement -> selenideElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public String getSearchResultLinkTextForIndex(int index) {
        return Selenide.$x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md']", index + 1)).getText();
    }

    public GoogleSearchImagesPage navigateToImagesPage() {
        Selenide.$x("//a[@class='hide-focus-ring']").click();
        return new GoogleSearchImagesPage();
    }
}

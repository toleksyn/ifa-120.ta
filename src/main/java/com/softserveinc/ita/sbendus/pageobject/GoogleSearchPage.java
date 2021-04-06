package com.softserveinc.ita.sbendus.pageobject;

import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchPage {

    public String getSearchResultLinkText(int index) {
        return $x(String.format("(%s)[%d]", "//div[@class='yuRUbf']/a", (index + 1))).getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return Selenide.$$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(SelenideElement -> SelenideElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public Integer getGoogleSearchResultAmount() {
        String googleSearchResult = $x("//div[@id='result-stats']").getText();
        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
                .substring(googleSearchResult.indexOf(":") + 1,
                        googleSearchResult.indexOf("("))
                .replaceAll(" ", ""));
        return googleSearchResultsAmount;
    }

    public GoogleSearchImagePage navigateToGoogleSearchImagePage() {
        $x("//a[@class='hide-focus-ring']").click();
        return new GoogleSearchImagePage();
    }

}


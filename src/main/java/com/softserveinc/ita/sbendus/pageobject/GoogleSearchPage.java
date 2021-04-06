package com.softserveinc.ita.sbendus.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {

    public ElementsCollection getSearchResultLinkText(int index) {
        return Selenide.$$x(String.format("(%s)[%d]", "//div[@class='yuRUbf']/a", (index + 1)));
    }

    public List<String> getListOfSearchResultLinks() {
        return Selenide.$$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(SelenideElement -> SelenideElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public Integer getGoogleSearchResultAmount() {
        String googleSearchResult = Selenide.$$x("//div[@id='result-stats']").toString();
        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
                .substring(googleSearchResult.indexOf(":") + 1,
                        googleSearchResult.indexOf("("))
                .replaceAll(" ", ""));
        return googleSearchResultsAmount;
    }
}


//    public Integer getGoogleSearchResultAmount() {
//        String googleSearchResult = getElement("//div[@id='result-stats']").getText();
//        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
//                .substring(googleSearchResult.indexOf(":") + 1,
//                        googleSearchResult.indexOf("("))
//                .replaceAll(" ", ""));
//        return googleSearchResultsAmount;
//    }

//
//    public GoogleSearchImagePage navigateToGoogleSearchImagePage() {
//        clickElement("//a[@class='hide-focus-ring']");
//        return new GoogleSearchImagePage();
//    }

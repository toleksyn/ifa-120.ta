package com.softserveinc.ita.sbendus.pageobject;

import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.ita.common.WebElementUtil.*;

public class GoogleSearchPage {

    public String getSearchResultLinkText(int index) {
        return getElementFromListByIndex("//div[@class='yuRUbf']/a", index)
                .getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(WebElement -> WebElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public Integer getGoogleSearchResultAmount() {
        String googleSearchResult = getElement("//div[@id='result-stats']").getText();
        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
                .substring(googleSearchResult.indexOf(":") + 1,
                        googleSearchResult.indexOf("("))
                .replaceAll(" ", ""));
        return googleSearchResultsAmount;
    }

    public GoogleSearchImagePage navigateToGoogleSearchImagePage() {
        clickElement("//a[@class='hide-focus-ring']");
        return new GoogleSearchImagePage();
    }
}
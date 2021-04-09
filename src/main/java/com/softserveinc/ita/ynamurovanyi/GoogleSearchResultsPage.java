package com.softserveinc.ita.ynamurovanyi;

import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public List<String> getSearchResultsLinks() {
        return Selenide.$$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(searchResultsLinks -> searchResultsLinks.attr("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesPage navigateToImagesResultsPage() {
        Selenide.$x("//a[@class='hide-focus-ring']").click();
        return new GoogleSearchImagesPage();
    }

    public GoogleSearchResultsPage navigateToResultsPageNumber(int targetPageNumber) {
        Selenide.$x("//a[contains(text()," + targetPageNumber + ")]").click();
        return this;
    }

    public int getSearchResultsPageNumber() {
        String pageNumber = Selenide.$x("//div[@id='result-stats']").text();
        pageNumber = pageNumber.substring(0, pageNumber.indexOf(":"));  //replace ":" with "f" for EN lang
        pageNumber = pageNumber.replaceAll("[^0-9]", "");
        if (pageNumber.isEmpty()) {
            pageNumber = "1";
        }
        return Integer.valueOf(pageNumber);
    }

    public String getSearchResultsLinkText(int linkIndex) {
        return Selenide.$x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md']", linkIndex + 1)).text();
    }
}


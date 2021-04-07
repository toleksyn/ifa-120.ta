package com.softserveinc.ita.romankhr;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    public String getSearchResultLinkTitle() {
        return $$x("//h3[@class='LC20lb DKV0Md']").first().toString().toLowerCase();
    }

    public List<String> getSearchResultsLinks() {
        return $$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchResultImagesPage navigateToImagesPage() {
        $x("//div[@class='MUFPAc']/div[2]");
        return new GoogleSearchResultImagesPage();
    }

    public GoogleSearchResultPage navigateToNumberedResultPage(int pageNumber) {
        $x("//a[contains(text()," + pageNumber + ")]");
        return this;
    }

    public String getSearchResultDescription(int numberOfItem) {
        return $x(String.format("(%s)[%d]", "//div[@class='IsZvec']", numberOfItem))
                .getText()
                .toLowerCase();
    }
}

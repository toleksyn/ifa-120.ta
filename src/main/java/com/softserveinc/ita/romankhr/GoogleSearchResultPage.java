package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {

    public String getSearchResultLinksTitles() {
        return WebElementUtil.getElementsList("//h3[@class='LC20lb DKV0Md']")
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).get(0).toLowerCase();
    }

    public List<String> getSearchResultsLinks() {
        return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchResultImagesPage navigateToImagesPage() {
        WebElementUtil.clickElement("//div[@class='MUFPAc']/div[2]");
        return new GoogleSearchResultImagesPage();
    }

    public GoogleSearchResultPage navigateToNumberedResultPage(int pageNumber) {
        WebElementUtil.clickElement("//a[contains(text()," + pageNumber + ")]");
        return this;
    }

    public String getSearchResultDescription(int numberOfItem) {
        return WebElementUtil.getElementFromListByIndex("//div[@class='IsZvec']", numberOfItem)
                .getText()
                .toLowerCase();
    }
}

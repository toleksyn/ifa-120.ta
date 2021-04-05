package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


public class GoogleSearchResultsPage {
    public List<String> getListOfSearchResultLinksText() {
        return WebElementUtil.getElementsList("//h3[@class='LC20lb DKV0Md']")
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getListOfSearchResultLinks() {
        return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public String getSearchResultLinkTextForIndex(int index) {
        return getListOfSearchResultLinksText().get(index);
    }

    public GoogleSearchImagesPage navigateToImagesPage() {
        WebElementUtil.getElementByIndex("//a[@class='hide-focus-ring']", 0).click();
        return new GoogleSearchImagesPage();
    }
}

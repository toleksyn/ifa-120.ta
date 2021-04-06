package com.softserveinc.ita.nromanchuk;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {

    public String getLinkText(int index) {
        return WebElementUtil.getElementFromListByIndex("//h3[@class='LC20lb DKV0Md']", index)
                .getText()
                .toLowerCase();
    }

    public List<String> getResultsLinks() {
        return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public ImagesResultsPage navigateToImageResultsPage() {
        WebElementUtil.clickElement("//*[@data-hveid='CAEQAw']");
        return new ImagesResultsPage();
    }

    public BooksResultsPage navigateToBooksResultsPage() {
        WebElementUtil.clickElement("//div[@class='GOE98c']");
        WebElementUtil.getElementFromListByIndex("//div[@class='gTMtLb fp-nh']//a", 1)
                .click();
        return new BooksResultsPage();
    }
}
package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public List<String> getSearchResultsLinksText() {
        return WebElementUtil.getElementsList("//h3[@class='LC20lb DKV0Md']")
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinks() {
        return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(searchResultsLinks -> searchResultsLinks.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesPage navigateToImagesResultsPage() {
        WebElementUtil.clickElement("//a[@class='hide-focus-ring']");
        return new GoogleSearchImagesPage();
    }

    public GoogleSearchResultsPage navigateToResultsPageNumber(int targetPageNumber) {
        WebElementUtil.clickElement("//a[contains(text()," + targetPageNumber + ")]");
        return this;
    }

    public int getSearchResultsPageNumber() {
        String pageNumber = TestRunner.getDriver()
                .findElement(By.xpath("//div[@id='result-stats']"))
                .getText();
        pageNumber = pageNumber.substring(0, pageNumber.indexOf(":"));  //replace ":" with "f" for EN lang
        pageNumber = pageNumber.replaceAll("[^0-9]", "");
        if (pageNumber.isEmpty()) {
            pageNumber = "1";
        }
        return Integer.valueOf(pageNumber);
    }
}


package com.softserveinc.ita.ynamurovanyi;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public List<String> getSearchResultsLinksText() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinks() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(searchResultsLinks -> searchResultsLinks.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesPage navigateToImagesResultsPage() {
        TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//a[@class='hide-focus-ring']"))
                .click();
        return new GoogleSearchImagesPage();
    }
}

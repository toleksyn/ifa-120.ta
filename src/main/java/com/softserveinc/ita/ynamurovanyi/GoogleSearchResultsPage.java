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

    public GoogleSearchResultsPage navigateToFifthResultsPage() {
        TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//tr//a"))
                .get(3)
                .click();
        return new GoogleSearchResultsPage();
    }

    public int getSearchResultsPageNumber() {
        String temp = TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//div[@id='result-stats']"))
                .getText();
        temp = temp.substring(0, temp.indexOf(":"));  //replace ":" with "f" for EN lang
        temp = temp.replaceAll("[^0-9]", "");
        if (temp.isEmpty()) {
            temp = "1";
        }
        return Integer.valueOf(temp);
    }
}


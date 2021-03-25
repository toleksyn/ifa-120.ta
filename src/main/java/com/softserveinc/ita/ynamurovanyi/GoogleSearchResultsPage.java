package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.pageobjects_task.ynamurovanyi.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public static List<String> getSearchResultsLinksText() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public static List<String> getSearchResultsLinks() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

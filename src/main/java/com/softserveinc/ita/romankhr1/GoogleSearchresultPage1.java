package com.softserveinc.ita.romankhr1;

import com.softserveinc.ita.pageobjects_task.romankhr1.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchresultPage1 {

    private String searchResultsLocator = "//h3[@class='LC20lb DKV0Md']";

    public List<String> getSearchResultLinks() {
        return Stream.of(TestRunner.threadLocalDriver.get().findElements(By.xpath(searchResultsLocator)))
                .map(webElement -> webElement.get(0).getText())
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinks() {
        return TestRunner.threadLocalDriver.get().findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

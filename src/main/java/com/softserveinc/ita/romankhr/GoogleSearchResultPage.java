package com.softserveinc.ita.romankhr;


import com.softserveinc.ita.pageobjects_task.romankhr.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {

    public List<String> getSearchResultLinks() {
        return Stream.of(TestRunner.threadLocalDriver.get().findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
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

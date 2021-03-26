package com.softserveinc.ita.romankhr;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {

    public List<String> getSearchResultLinks() {
        return TestRunner.threadLocalDriver.get().findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinks() {
        return TestRunner.threadLocalDriver.get().findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

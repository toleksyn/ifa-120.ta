package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {

    public String getFirstLinkText() {
        return Stream.of(TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(element -> element.get(0).getText().toLowerCase())
                .collect(Collectors.toList()).get(0);
    }

    public List<String> resultsLinks() {
        return TestRunner
                .driver
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

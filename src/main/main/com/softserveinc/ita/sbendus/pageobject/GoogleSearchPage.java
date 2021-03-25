package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {

    public String getSearchResultsLink() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .get(0);
    }

    public List<String> getListOfSearchResultLinks() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(WebElement -> WebElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}
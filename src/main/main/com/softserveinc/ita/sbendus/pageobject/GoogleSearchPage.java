package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {

    private By googleSearchListOfLinks = By.xpath("//div[@class='yuRUbf']/a");

    public String getSearchAllResultLinks() {
        return TestRunner
                .getDriver()
                .findElements(googleSearchListOfLinks)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .get(0);
    }

    public List<String> getListOfSearchResultLinks() {
        return TestRunner
                .getDriver()
                .findElements(googleSearchListOfLinks)
                .stream()
                .map(WebElement -> WebElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}
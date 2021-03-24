package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

public class GoogleSearchPage {

    private By googleSearchListOfLinks = By.xpath("//h3[@class='LC20lb DKV0Md']");

    public String getSearchResultsLink() {
        return TestRunner
                .getDriver()
                .findElements(googleSearchListOfLinks)
                .stream().map(WebElement::getText)
                .collect(Collectors.toList())
                .get(0);
    }
}
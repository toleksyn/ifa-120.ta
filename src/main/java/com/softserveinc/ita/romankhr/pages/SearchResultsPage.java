package com.softserveinc.ita.romankhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchResultsPage extends Page {
    public SearchResultsPage(RemoteWebDriver driver) {
        super(driver);
    }
    private String searchResultsLocator="//div[@class='B6fmyf']";

    public List<String> getSearchResultsLinks(){
        return  Stream.of(driver.findElements(By.xpath(searchResultsLocator)))
                .map(el ->el.get(0).getText())
                .collect(Collectors.toList());
    }
}

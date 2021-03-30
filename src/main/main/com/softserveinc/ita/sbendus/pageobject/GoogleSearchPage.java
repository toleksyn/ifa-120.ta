package com.softserveinc.ita.sbendus.pageobject;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {

    public String getSearchResultLinkText(int index) {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .get(index);
    }

    public List<String> getListOfSearchResultLinks() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(WebElement -> WebElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public Integer getGoogleSearchResultAmount() {
        String googleSearchResult = TestRunner.getDriver()
                .findElement(By.xpath("//div[@id='result-stats']"))
                .getText();
        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
                .substring(googleSearchResult.indexOf(":") + 1,
                        googleSearchResult.indexOf("("))
                .replaceAll(" ", ""));

        return googleSearchResultsAmount;
    }

    public GoogleSearchImagePage navigateToGoogleSearchImagePage() {
        WebElement imagesSearchType = TestRunner.getDriver().findElement(By.xpath("//a[@class='hide-focus-ring']"));
        imagesSearchType.click();
        return new GoogleSearchImagePage();
    }
}
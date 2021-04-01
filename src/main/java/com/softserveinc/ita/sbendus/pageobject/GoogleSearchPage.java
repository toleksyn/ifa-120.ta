package com.softserveinc.ita.sbendus.pageobject;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.ita.common.WebElementUtil.*;

public class GoogleSearchPage {

    public String getSearchResultLinkText(int index) {

        return (getElementFromListForIndex("//div[@class='yuRUbf']/a", index))
                .getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return getListOfElements("//div[@class='yuRUbf']/a")
                .stream()
                .map(WebElement -> WebElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public Integer getGoogleSearchResultAmount() {
        String googleSearchResult = TestRunner.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='result-stats']")))
                .getText();
        Integer googleSearchResultsAmount = Integer.parseInt(googleSearchResult
                .substring(googleSearchResult.indexOf(":") + 1,
                        googleSearchResult.indexOf("("))
                .replaceAll(" ", ""));
        return googleSearchResultsAmount;
    }

    public GoogleSearchImagePage navigateToGoogleSearchImagePage() {
        clickElement("//a[@class='hide-focus-ring']");
        return new GoogleSearchImagePage();
    }
}
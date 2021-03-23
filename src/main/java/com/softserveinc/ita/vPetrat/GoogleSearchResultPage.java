package com.softserveinc.ita.vPetrat;

import com.softserveinc.ita.pageobjects_task.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {
    private By searchResultsLocator = By.xpath("//div[@class='B6fmyf']");

    public List<String> collectSearchResults() {
        TestRunner.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultsLocator));
        return Stream.of(TestRunner.getDriver().findElements(searchResultsLocator)).map(r->r.get(0).getText()).collect(Collectors.toList());
    }
}

package com.softserveinc.ita.vPetrat.pageObjects;

import com.softserveinc.ita.pageobjects_task.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Collectors;

public class GoogleSearchResultPage {
    private By searchResultLink = By.xpath("//h3[@class='LC20lb DKV0Md']");

    public String getTextOfFirstLinkInSearchResults() {
        TestRunner.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultLink));
        return  TestRunner.getDriver().findElements(searchResultLink).stream().map(WebElement::getText).collect(Collectors.toList()).get(0);
    }
}

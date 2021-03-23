package com.softserveinc.ita.vPetrat.pageObjects;

import com.softserveinc.ita.pageobjects_task.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePage {

    private  By searchInput = By.xpath("//input[@class='gLFyf gsfi']");

    public GoogleHomePage openHomePage() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchForTheSpecifiedRequest(String request) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(searchInput));
        WebElement searchInputElement = TestRunner.getDriver().findElement(searchInput);
        searchInputElement.sendKeys(request);
        searchInputElement.sendKeys(Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

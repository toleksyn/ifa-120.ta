package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String request) {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='gLFyf gsfi']")))
                .sendKeys(request + Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    public GoogleLuckySearchResultPage luckySearchFor(String request) {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='gLFyf gsfi']")))
                .sendKeys(request);
        TestRunner
                .getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='btnI']")))
                .get(0).click();
        return new GoogleLuckySearchResultPage();
    }
}

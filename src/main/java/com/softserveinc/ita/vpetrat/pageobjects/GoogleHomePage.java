package com.softserveinc.ita.vpetrat.pageobjects;

import com.softserveinc.ita.vpetrat.testrunner.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String request) {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='gLFyf gsfi']")))
                .sendKeys(request + Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

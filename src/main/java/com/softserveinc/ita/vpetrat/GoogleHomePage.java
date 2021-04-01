package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String request) {
        WebElementUtil.setValueForElement("//input[@class='gLFyf gsfi']", request + Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    public GoogleLuckySearchResultPage luckySearchFor(String request) {
        WebElementUtil.setValueForElement("//input[@class='gLFyf gsfi']", request);
        WebElementUtil.getElementFromListForIndex("//input[@name='btnI']", 0).click();
        return new GoogleLuckySearchResultPage();
    }
}

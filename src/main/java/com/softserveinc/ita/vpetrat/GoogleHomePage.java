package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String request) {
        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", request + Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    public GoogleLuckySearchResultPage luckySearchFor(String request) {
        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", request);
        WebElementUtil.getElementsList("//input[@name='btnI']").get(0).click();
        return new GoogleLuckySearchResultPage();
    }
}

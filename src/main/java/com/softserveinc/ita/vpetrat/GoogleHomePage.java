package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        WebElementUtil.openURL("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String request) {
        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", request + Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    public GoogleLuckySearchResultPage luckySearchFor(String request) {
        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", request);
        WebElementUtil.getElementByIndex("//input[@name='btnI']", 0).click();
        return new GoogleLuckySearchResultPage();
    }
}

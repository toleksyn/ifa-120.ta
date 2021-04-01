package com.softserveinc.ita.sbendus.pageobject;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.Keys;

import static com.softserveinc.ita.common.WebElementUtil.setValueForElement;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchPage searchFor(String searchTerm) {
        setValueForElement("//input[@class='gLFyf gsfi']", searchTerm + Keys.ENTER);
        return new GoogleSearchPage();
    }
}

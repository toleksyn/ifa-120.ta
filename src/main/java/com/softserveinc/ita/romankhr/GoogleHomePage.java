package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver()
                .get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

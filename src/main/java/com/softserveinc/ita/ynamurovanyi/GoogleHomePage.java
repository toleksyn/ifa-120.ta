package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver()
                .get("http://google.com");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String searchTerm) {
        WebElementUtil.setElementValue("//input", searchTerm + Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    public String getTitle() {
        return TestRunner.getDriver().getTitle();
    }
}

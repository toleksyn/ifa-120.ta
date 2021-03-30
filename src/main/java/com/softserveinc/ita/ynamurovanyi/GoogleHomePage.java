package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver()
                .get("http://google.com");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String searchTerm) {
        TestRunner.getDriver()
                .findElement(By.xpath("//input"))
                .sendKeys(searchTerm);
        TestRunner.getDriver()
                //Search Button
                .findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']/center/input[1]"))
                .click();
        return new GoogleSearchResultsPage();
    }

    public String getTitle() {
        return TestRunner.getDriver().getTitle();
    }
}

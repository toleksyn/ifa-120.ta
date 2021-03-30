package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver()
                .get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        TestRunner.getDriver().findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }

}

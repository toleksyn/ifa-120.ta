package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.pageobjects_task.romankhr.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage {
    private String searchFieldLocator = "//input[@class='gLFyf gsfi']";
     public GoogleHomePage open() {
        TestRunner.threadLocalDriver
                .get()
                .get("https://www.google.com/");
        return this;
    }

    public GoogleSearchresultPage searchFor(String searchTerm) {

        TestRunner.threadLocalDriver.get().findElement(By.xpath(searchFieldLocator)).sendKeys(searchTerm+ Keys.ENTER);
        return new GoogleSearchresultPage();
    }
}

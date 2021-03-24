package com.softserveinc.ita.romankhr1;

import com.softserveinc.ita.pageobjects_task.romankhr1.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage1 {
    private String searchFieldLocator = "//input[@class='gLFyf gsfi']";
     public GoogleHomePage1 open() {
        TestRunner.threadLocalDriver
                .get()
                .get("https://www.google.com/");
        return this;
    }

    public GoogleSearchresultPage1 searchFor(String searchTerm) {

        TestRunner.threadLocalDriver.get().findElement(By.xpath(searchFieldLocator)).sendKeys(searchTerm+ Keys.ENTER);
        return new GoogleSearchresultPage1();
    }
}

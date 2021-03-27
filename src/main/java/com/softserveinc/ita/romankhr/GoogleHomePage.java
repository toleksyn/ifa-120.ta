package com.softserveinc.ita.romankhr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.threadLocalDriver
                .get()
                .get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        TestRunner.threadLocalDriver.get().findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }

}

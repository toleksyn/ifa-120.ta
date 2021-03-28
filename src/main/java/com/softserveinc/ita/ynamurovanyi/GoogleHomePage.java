package com.softserveinc.ita.ynamurovanyi;

import org.openqa.selenium.By;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.threadLocalDriver
                .get()
                .get("http://google.com");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String searchTerm) {
        TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//input"))
                .sendKeys(searchTerm);
        TestRunner.threadLocalDriver
                .get()
                //Search Button
                .findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']/center/input[1]"))
                .click();
        return new GoogleSearchResultsPage();
    }

    public String getTitle() {
        return TestRunner.threadLocalDriver
                .get()
                .getTitle();
    }
}

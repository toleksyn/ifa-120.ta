package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.pageobjects_task.ynamurovanyi.TestRunner;
import org.openqa.selenium.By;

public class GoogleHomePage {

    private By searchInputField = By.xpath("//input");

    public GoogleHomePage open() {
        TestRunner.threadLocalDriver
                .get()
                .get("http://google.com");
        return this;
    }

    public GoogleSearchResultsPage searchFor(String searchTerm) {
        TestRunner.threadLocalDriver
                .get()
                .findElement(searchInputField)
                .sendKeys(searchTerm);
        TestRunner.threadLocalDriver
                .get()
                //Search Button
                .findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']/center/input[1]"))
                .click();
        return new GoogleSearchResultsPage();
    }
}

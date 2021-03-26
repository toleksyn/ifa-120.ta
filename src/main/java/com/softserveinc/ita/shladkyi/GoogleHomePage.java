package com.softserveinc.ita.shladkyi;


import com.softserveinc.ita.pageobjects_task.shladkyi.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.driver.get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        TestRunner.driver
                .findElement(By.cssSelector("[class='gLFyf gsfi']"))
                .sendKeys(searchTerm, Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

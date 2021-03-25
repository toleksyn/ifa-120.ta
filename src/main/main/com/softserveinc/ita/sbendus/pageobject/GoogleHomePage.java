package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchPage searchFor(String searchTerm) {
        WebElement googleSearchInput = TestRunner.getDriver().findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        googleSearchInput.sendKeys(searchTerm);
        googleSearchInput.sendKeys(Keys.ENTER);
        return new GoogleSearchPage();
    }
}

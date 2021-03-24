package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class GoogleHomePage {

    private By searchInput = By.xpath("//input[@class='gLFyf gsfi']");

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchPage searchFor(String searchTerm) {
        WebElement googleSearchInput = TestRunner.getDriver().findElement(searchInput);
        googleSearchInput.sendKeys(searchTerm);
        googleSearchInput.sendKeys(Keys.ENTER);
        return new GoogleSearchPage();
    }
}

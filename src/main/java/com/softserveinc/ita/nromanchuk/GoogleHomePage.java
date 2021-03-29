package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        TestRunner.getDriver()
                .findElement(By.xpath("//input[@name='q']"))
                .sendKeys(searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }
}

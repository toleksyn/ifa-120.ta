package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleHomePage {
    public GoogleHomePage open() {
        new ChromeDriver().get("http://google.com");
        return this;
    }

    public void searchFor(String searchTerm) {
        new ChromeDriver()
                .findElementById("input")
                .sendKeys(searchTerm);
        new ChromeDriver()
                .findElement(By.tagName(""))

    }
}

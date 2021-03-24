package com.softserveinc.ita.romankhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected RemoteWebDriver driver;
    protected WebDriverWait wait;

    public Page(final RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    protected WebElement getElement(By selector) {

        try {
            return driver.findElement(selector);
        } catch (Exception ex) {
            return null;
        }
    }
}

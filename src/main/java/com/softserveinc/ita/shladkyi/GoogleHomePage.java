package com.softserveinc.ita.shladkyi;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


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

    public GoogleSettingsPage openSettings() {
        TestRunner.driver.findElement(By.id("Mses6b")).click();
        WebElement searchSettingsButton = TestRunner.driver.findElement(By.xpath("//a[@class='EzVRq']"));
        TestRunner.wait.until(ExpectedConditions.elementToBeClickable(searchSettingsButton));
        searchSettingsButton.click();
        return new GoogleSettingsPage();
    }

    public String getTextOfSearchButton() {
        return TestRunner.driver.findElement(By.cssSelector("[name='btnK']")).getAttribute("value");
    }

    public String getTextOfLuckyButton() {
        return TestRunner.driver.findElement(By.cssSelector("[name='btnI']")).getAttribute("value");
    }
}

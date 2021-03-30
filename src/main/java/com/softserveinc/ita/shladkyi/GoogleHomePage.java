package com.softserveinc.ita.shladkyi;


import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class GoogleHomePage {

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        TestRunner.getDriver()
                .findElement(By.cssSelector("[class='gLFyf gsfi']"))
                .sendKeys(searchTerm, Keys.ENTER);
        return new GoogleSearchResultPage();
    }

    public GoogleSettingsPage openSettings() {
        TestRunner.getDriver().findElement(By.id("Mses6b")).click();
        WebElement searchSettingsButton = TestRunner.getDriver().findElement(By.xpath("//a[@class='EzVRq']"));
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(searchSettingsButton));
        searchSettingsButton.click();
        return new GoogleSettingsPage();
    }

    public String getTextOfSearchButton() {
        return TestRunner.getDriver().findElement(By.cssSelector("[name='btnK']")).getAttribute("value");
    }

    public String getTextOfLuckyButton() {
        return TestRunner.getDriver().findElement(By.cssSelector("[name='btnI']")).getAttribute("value");
    }
}

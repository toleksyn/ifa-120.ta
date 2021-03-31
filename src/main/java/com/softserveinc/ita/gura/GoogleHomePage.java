package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleHomePage extends TestRunner {
    public GoogleHomePage() {}

    public GoogleHomePage(String link) {
        driver.get(link);
    }

    public GoogleHomePage openPage() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        return this;
    }

    public GoogleHomePage openPageRemoteWebDriver() {
        remoteWebDriver.get("https://www.google.com");
        remoteWebDriver.manage().window().maximize();
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        WebElement searchInputField = driver.findElement(By.xpath("//input[@name='q']"));
        searchInputField.sendKeys(inputText);
        searchInputField.sendKeys(Keys.ENTER);
        return new GoogleResultPage();
    }

    public boolean checkIfPageOpened() {
        return driver.findElement(By.xpath("//input[@class='lnXdpd']")).getAttribute("class").contains("lnXdpd");
    }

    public GoogleHomePage hideGoogleLogo() {
        JavascriptExecutor js = remoteWebDriver;

        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
        return this;
    }
}

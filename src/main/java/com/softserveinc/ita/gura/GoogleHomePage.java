package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {
    public GoogleHomePage() {}

    public GoogleHomePage(String link) {
        TestRunner.getDriver().get(link);
    }

    public GoogleHomePage openPage() {
        TestRunner.getDriver().get("https://www.google.com");
        TestRunner.getDriver().manage().window().maximize();
        return this;
    }

    public GoogleHomePage openPageRemoteWebDriver() {
        TestRunner.getRemoteWebDriver().get("https://www.google.com");
        TestRunner.getRemoteWebDriver().manage().window().maximize();
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        WebElement searchInputField = TestRunner.getDriver().findElement(By.xpath("//input[@name='q']"));
        searchInputField.sendKeys(inputText);
        searchInputField.sendKeys(Keys.ENTER);
        return new GoogleResultPage();
    }

    public boolean checkIfPageOpened() {
        return TestRunner.getDriver().findElement(By.xpath("//input[@class='lnXdpd']")).getAttribute("class").contains("lnXdpd");
    }

    public void hideGoogleLogo() {
        JavascriptExecutor js = TestRunner.getRemoteWebDriver();
        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkHideGoogleLogo() {

        return false;
    }
}

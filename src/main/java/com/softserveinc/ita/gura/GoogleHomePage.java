package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {
    public GoogleHomePage openGoogleHomePage() {
        TestRunner.getDriver().get("https://www.google.com");
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        WebElement searchInputField = TestRunner.getDriver().findElement(By.xpath("//input[@name='q']"));
        searchInputField.sendKeys(inputText);
        searchInputField.sendKeys(Keys.ENTER);
        return new GoogleResultPage();
    }

    public boolean isPageOpened() {
        return TestRunner.getDriver().findElement(By.xpath("//input[@class='lnXdpd']")).getAttribute("class").contains("lnXdpd");
    }

    public void hideGoogleLogo() {
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.getDriver();
        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkGoogleLogoVisibility() {
        return TestRunner.getDriver().findElement(By.xpath("//img[@class='lnXdpd']")).getAttribute("style").contains("visibility:hidden");
    }
}

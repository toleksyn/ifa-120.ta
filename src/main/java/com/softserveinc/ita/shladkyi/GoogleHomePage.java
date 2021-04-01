package com.softserveinc.ita.shladkyi;


import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
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
        WebElementUtil.setValueForElement("//input[@class='gLFyf gsfi']", searchTerm + Keys.ENTER);
        return new GoogleSearchResultPage();
    }

    public GoogleSettingsPage openSettingsPage() {
        WebElementUtil.clickElement("//button[@id='Mses6b']");
        WebElementUtil.clickElement("//a[@class='EzVRq']");
        return new GoogleSettingsPage();
    }

    public String getSearchButtonText() {
        return TestRunner.getDriver().findElement(By.xpath("//input[@name='btnK']")).getAttribute("value");
    }

    public String getLuckyButtonText() {
        return TestRunner.getDriver().findElement(By.xpath("//input[@name='btnI']")).getAttribute("value");
    }
}

package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends TestRunner {
    public GoogleHomePage openPage() {
        TestRunner.driver.get("https://www.google.com");
        TestRunner.driver.manage().window().maximize();
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
}

package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleHomePage {
    protected static WebDriver driver = new ChromeDriver();;

    public GoogleHomePage openGoogleSearch() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        return this;
    }

    public void doSearch(String inputText) {
        WebElement searchInputField = driver.findElement(By.xpath("//input[@name='q']"));
        searchInputField.sendKeys(inputText);
        searchInputField.sendKeys(Keys.ENTER);
    }
}

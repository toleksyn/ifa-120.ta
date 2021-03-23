package com.softserveinc.ita.pageobjects_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class TestRunner {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    @BeforeSuite
    public void loadWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/softserveinc/ita/pageobjects_task/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }
}

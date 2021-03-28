package com.softserveinc.ita.ynamurovanyi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestRunner {

    public static ThreadLocal<WebDriver> threadLocalDriver;

    @BeforeSuite
    public void setUpChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        threadLocalDriver = new ThreadLocal<>();
        threadLocalDriver.set(new ChromeDriver());
        threadLocalDriver.get().manage().window().maximize();
    }

    @AfterSuite
    public void quitChromeWebDriver() {
        threadLocalDriver.get().quit();
    }
}

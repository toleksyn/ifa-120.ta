package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setUpConfiguration() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sofia\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}

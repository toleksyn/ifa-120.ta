package com.softserveinc.ita.pageobjects_task.romankhr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class TestRunner {
    public static ThreadLocal<WebDriver> threadLocalDriver;

    @BeforeSuite
    public void setUp(){
        threadLocalDriver=new ThreadLocal<>();
        System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
        threadLocalDriver.set(new ChromeDriver());
        threadLocalDriver.get().manage().window().maximize();

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        if (TestRunner.threadLocalDriver.get() != null) {
            TestRunner.threadLocalDriver.get().quit();
        }
    }
}

package com.softserveinc.ita.pageobjects_task.romankhr;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners()
public abstract class BaseTest {

    protected RemoteWebDriver driver = null;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }

}

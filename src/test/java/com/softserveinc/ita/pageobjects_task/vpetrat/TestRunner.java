package com.softserveinc.ita.pageobjects_task.vpetrat;

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
    public static void setupWebDriverTypeAndProperties() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/com/softserveinc/ita/vPetrat/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }
}

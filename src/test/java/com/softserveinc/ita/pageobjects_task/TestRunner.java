package com.softserveinc.ita.pageobjects_task;

import com.softserveinc.ita.vPetrat.resources.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
        String browserName = null;
        try(InputStream inputStream = new FileInputStream("src/main/java/com/softserveinc/ita/vPetrat/resources/browserConfiguration.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            browserName = properties.getProperty("browserNameKey");
        } catch (IOException io) {
            io.printStackTrace();
        }
        DriverType driverType = DriverType.initDriverType(browserName);
        System.setProperty(driverType.getDriverKey(), driverType.getDriverPath());
        driver =DriverType.createRemoteDriverObject(browserName);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}

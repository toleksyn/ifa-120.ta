package com.softserveinc.ita.gura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestRunner {
    private static WebDriver driver;
    private static ThreadLocal<WebDriver> threadLocalDriver;
    private static RemoteWebDriver remoteWebDriver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        TestRunner.driver = driver;
    }

    public static ThreadLocal<WebDriver> getThreadLocalDriver() {
        return threadLocalDriver;
    }

    public static void setThreadLocalDriver(ThreadLocal<WebDriver> threadLocalDriver) {
        TestRunner.threadLocalDriver = threadLocalDriver;
    }

    public static RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriver;
    }

    public static void setRemoteWebDriver(RemoteWebDriver remoteWebDriver) {
        TestRunner.remoteWebDriver = remoteWebDriver;
    }
}

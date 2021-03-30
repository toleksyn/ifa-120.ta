package com.softserveinc.ita.gura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestRunner {
    public static WebDriver driver;

    protected static ThreadLocal<WebDriver> threadLocalDriver;

    public static RemoteWebDriver remoteWebDriver;
}

package com.softserveinc.ita.gura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {
    protected static WebDriver driver = new ChromeDriver();

    public static ThreadLocal<WebDriver> threadLocalDriver;
}

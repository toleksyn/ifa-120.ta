package com.softserveinc.ita.pageobjects_task.kuguk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

	
public class TestRunner {
	public static WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
	driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
	}	
}

package com.softserveinc.ita.kuguk;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.softserveinc.ita.pageobjects_task.kuguk.GoogleTest;
import com.softserveinc.ita.pageobjects_task.kuguk.TestRunner;

public class GoogleHomePage {

	

	public GoogleHomePage open() {
		GoogleTest.driver.get("https://www.google.com");
		return this;
	}

	public GoogleSearchResultsPage searchFor(String searchString) {

		By searchField = By.xpath("//input[@name='q']");
		TestRunner.driver.findElement(searchField).sendKeys(searchString);

		TestRunner.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		TestRunner.driver.findElement(By.xpath("//input[@name='btnK']")).click();
		return new GoogleSearchResultsPage(); 
		}
}

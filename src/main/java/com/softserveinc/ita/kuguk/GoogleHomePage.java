package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;

import com.softserveinc.ita.pageobjects_task.kuguk.GoogleTest;

public class GoogleHomePage {
	
	private By searchField	= By.xpath("//input[@name='q']");
		
	public GoogleHomePage open () {
		GoogleTest.driver.get("https://www.google.com");
			return this;
	}
	 
	public GoogleSearchResultsPage searchFor(String searchString) { 
		
		GoogleTest.driver
				.findElement(searchField)
				.sendKeys(searchString);
		GoogleTest.driver
				.findElement(By.xpath("//input[@name='btnK']")).click();		
		return new GoogleSearchResultsPage();
	}
	
}

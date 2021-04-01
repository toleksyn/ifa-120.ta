package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

public class GoogleHomePage {

	public GoogleHomePage open() {
		TestRunner.getDriver().get("https://www.google.com");
		return this;
	}

	public GoogleSearchResultsPage searchFor(String searchString) {
		TestRunner.getWait()
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")))
				.sendKeys(searchString + Keys.ENTER);
		return new GoogleSearchResultsPage();
	}

}

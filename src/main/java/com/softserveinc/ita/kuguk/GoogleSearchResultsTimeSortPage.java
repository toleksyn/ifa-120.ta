package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

public class GoogleSearchResultsTimeSortPage {

	public String[] getSearchTimeSortResultsText() {				
		TestRunner.getWait()
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'mins ago')]")));
		
		return TestRunner.getDriver().findElements(By.xpath("//span[contains(text(),'mins ago')]"))
				.stream()
				.map(timeStampsOfFoundResults -> timeStampsOfFoundResults.getText())
				.toArray(String[]::new);
	}
}

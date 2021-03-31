package com.softserveinc.ita.kuguk;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

public class GoogleSearchResultsTimeSortPage {

	public List<String> getSearchTimeSortResultsText() {				
		
			return TestRunner.getWait()
	                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'mins ago')]")))
					.stream()
					.map(timeStampsOfFoundResults -> timeStampsOfFoundResults.getText())
					.collect(Collectors.toList()); }
}

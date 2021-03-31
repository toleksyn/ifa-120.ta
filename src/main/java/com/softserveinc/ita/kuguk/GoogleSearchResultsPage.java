package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

	public List<String> getSearchResultsLinksText() {
		return TestRunner.getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[@class='LC20lb DKV0Md']")))
				.stream()
				.map(foundLinksList -> foundLinksList.getText())
				.collect(Collectors.toList());
	}

	public List<String> getSearchResultsLinksList() {
		return TestRunner.getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yuRUbf']/a")))
				.stream()
				.map(foundLinksList -> foundLinksList.getAttribute("href"))
				.collect(Collectors.toList());
	}

	public GoogleSearchResultsImagesPage navigateToImagePage() {
		TestRunner.getWait()
				.until(ExpectedConditions.elementToBeClickable(TestRunner.getDriver().findElement(By.xpath("//*[@data-hveid='CAEQAw']"))))
				.click();
		return new GoogleSearchResultsImagesPage();
	}
	
	public GoogleSearchResultsTimeSortPage sortByPastHour() {
		TestRunner.getWait()
				.until(ExpectedConditions.elementToBeClickable(TestRunner.getDriver()
				.findElement(By.xpath("//div[@id='hdtb-tls']"))))
				.click();
				
		TestRunner.getWait()
				.until(ExpectedConditions.elementToBeClickable(TestRunner.getDriver()
				.findElement(By.xpath("//div[@id='hdtbMenus']/span[1]"))))		
				.click();
		
		TestRunner.getWait()
				.until(ExpectedConditions.elementToBeClickable(TestRunner.getDriver()
				.findElement(By.xpath("//a[contains(text(),'Past hour')]"))))
				.click();
		return new GoogleSearchResultsTimeSortPage();
	}
}

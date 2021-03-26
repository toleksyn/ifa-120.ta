package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.ita.pageobjects_task.kuguk.TestRunner;

public class GoogleSearchResultsPage {

	public List<String> getSearchResultsLinks() {
		return 	TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))			
				.stream()
				.map(foundLinksList -> foundLinksList.getText())
				.collect(Collectors.toList()); }
	
	public List<String> getSearchResultsLinksList() {
		return TestRunner.driver.findElements(By.xpath("//div[@class='yuRUbf']/a"))
				.stream()
				.map(foundLinksList -> foundLinksList.getAttribute("href"))
				.collect(Collectors.toList()); }
}

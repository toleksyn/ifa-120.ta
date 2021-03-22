package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;

import com.softserveinc.ita.pageobjects_task.kuguk.GoogleTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
	
public class GoogleSearchResultsPage {
	
	public List<String> getSearchResultsLinks () { 
	return Stream.of (GoogleTest.driver.findElements(By.xpath("//a[@link]"))) 
			.map(el -> el.get(0).getText ()) 
			.collect(Collectors.toList());
	}	
}

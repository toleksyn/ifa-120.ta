package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.softserveinc.ita.pageobjects_task.kuguk.TestRunner;	

public class GoogleSearchResultsPage {
	
	public List<String> getSearchResultsLinks () { 	
	  return 
			Stream.of (TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))  		
			.map(foundLinksList -> foundLinksList.get(0).getText ()) 
			.collect(Collectors.toList());

	//Get random link from search results
	//	        Stream.of (TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))  		
	//			.map(foundLinksList -> foundLinksList.get((int)(Math.random()*(foundLinksList.size()-1))).getText ()) 
	//			.collect(Collectors.toList());
	  
	}	
}

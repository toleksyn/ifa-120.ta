package com.softserveinc.ita.pageobjects_task.kuguk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserveinc.ita.kuguk.GoogleHomePage;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest extends TestRunner {
	
	private List<String> searchResultsLinks; 
	private String  searchString = "funny kitten";
	
@BeforeMethod
public void doSearch() { 
	
	//driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
	driver = new ChromeDriver();
	searchResultsLinks = new GoogleHomePage()
			.open ()
			.searchFor(searchString)
			.getSearchResultsLinks();
}


@Test
public void testGoogleSearch() {
	//assertTrue(false); (searchResultsLinks.contains(searchString)) .isTrue(); }
    assertTrue(searchResultsLinks.contains(searchString)) ; }
}

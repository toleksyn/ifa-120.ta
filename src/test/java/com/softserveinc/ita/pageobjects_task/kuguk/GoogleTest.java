package com.softserveinc.ita.pageobjects_task.kuguk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserveinc.ita.kuguk.GoogleHomePage;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest extends TestRunner {
	
	private List<String> searchResultsLinks; 
	private String searchString = "funny kitten";
	
@BeforeMethod
public void doSearch() { 
	searchResultsLinks = new GoogleHomePage()
			.open ()
			.searchFor(searchString)
			.getSearchResultsLinks();
}

@Test
public void testGoogleSearch_ExactMatchSearchString() {
    assertTrue
        (searchResultsLinks.toString().toLowerCase().contains(searchString)); 
}

@Test
public void testGoogleSearch() {
assertTrue
        (searchResultsLinks.toString().toLowerCase().contains("funny") 
        && searchResultsLinks.toString().toLowerCase().contains("kitten")); }
}

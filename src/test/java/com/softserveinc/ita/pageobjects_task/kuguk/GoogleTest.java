package com.softserveinc.ita.pageobjects_task.kuguk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserveinc.ita.kuguk.GoogleHomePage;

import static org.testng.Assert.assertTrue;
import java.util.List;

public class GoogleTest extends TestRunner {

	private List<String> searchResultsLinks;

	@Test
	public void testGoogleSearch() {
		searchResultsLinks = new GoogleHomePage()
				.open()
				.searchFor("funny kitten")
				.getSearchResultsLinks();
	assertTrue
	        (searchResultsLinks.toString().toLowerCase().contains("funny kitten")); }
	
	@Test
	public void testGoogleSearch_PresenceResultInList() {
		searchResultsLinks = new GoogleHomePage()
				.open()
				.searchFor("smartphone")
				.getSearchResultsLinksList();
	assertTrue(searchResultsLinks.toString().toLowerCase().contains("wikipedia.")); }
}

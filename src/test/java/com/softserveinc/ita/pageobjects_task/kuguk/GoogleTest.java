package com.softserveinc.ita.pageobjects_task.kuguk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserveinc.ita.kuguk.GoogleHomePage;

import static org.testng.Assert.assertTrue;
import java.util.List;

public class GoogleTest extends TestRunner {

	private List<String> searchResultsLinks;
	private GoogleHomePage openGoogleHome;

	@BeforeMethod
	public void doSearch() {
		openGoogleHome = new GoogleHomePage().open();
	}

	@Test
	public void testGoogleSearch() {
		searchResultsLinks = openGoogleHome.searchFor("funny kitten").getSearchResultsLinks();
		assertTrue(searchResultsLinks.get(0).toLowerCase().contains("funny kitten"));
	}

	@Test
	public void testGoogleSearch_PresenceResultInList() {
		searchResultsLinks = openGoogleHome.searchFor("smartphone").getSearchResultsLinksList();
		assertTrue(searchResultsLinks.toString().toLowerCase().contains("wikipedia."));
	}
}

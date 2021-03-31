package com.softserveinc.ita.pageobjects_task.kuguk;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import com.softserveinc.ita.kuguk.GoogleHomePage;
import com.softserveinc.ita.kuguk.GoogleSearchResultsImagesPage;
import com.softserveinc.ita.kuguk.GoogleSearchResultsTimeSortPage;
import com.softserveinc.ita.common.TestRunner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

	private GoogleHomePage openGoogleHomePage;
	private List<String> searchResultsLinks;
	private String searchString;

	@BeforeMethod
	public void doSearch() {
		openGoogleHomePage = new GoogleHomePage().open();
		searchString = "funny kitten";
	}

	@Test
	public void testGoogleSearch() {
		searchResultsLinks = openGoogleHomePage.searchFor(searchString).getSearchResultsLinksText();
		assertTrue(searchResultsLinks.get(0).toLowerCase().contains("funny kitten"));
	}

	@Test
	public void testGoogleSearch_PresenceResultInList() {
		searchResultsLinks = openGoogleHomePage.searchFor("smartphone").getSearchResultsLinksList();
		assertTrue(searchResultsLinks.toString().toLowerCase().contains("wikipedia."));
	}

	@Test
	public void testGoogleSearchImages() {
		String testStringForAssert = "funny";
		GoogleSearchResultsImagesPage searchResultsImagesPage = openGoogleHomePage.searchFor(searchString)
				.navigateToImagePage();

		List<String> searchResultsImages = searchResultsImagesPage.getSearchResultsImagesText();

		assertTrue(searchResultsImages.size() > 9);

		assertTrue(searchResultsImages.get(0).toLowerCase().contains(testStringForAssert));
		assertTrue(searchResultsImages.get(4).toLowerCase().contains(testStringForAssert));

		searchResultsImagesPage.gotoGoogleHomePageByLogo();

		assertEquals(TestRunner.getDriver().getTitle(), "Google", "Current Page isn't a Google homepage");
	}

	@Test
	public void testGoogleTimeSortSearch() {

		TestRunner.getDriver().get("https://www.google.com.ua/?hl=en");
		GoogleSearchResultsTimeSortPage searchResultsTimeSort = openGoogleHomePage.searchFor("webdriver")
				.sortByPastHour();

		List<String> searchTimeSortResults = searchResultsTimeSort.getSearchTimeSortResultsText();

		assertTrue(searchTimeSortResults.get(0).toLowerCase().contains("mins ago"));
	}
}

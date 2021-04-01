package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

	public List<String> getSearchResultsLinksText() {
		return WebElementUtil.getListOfElements("//h3[@class='LC20lb DKV0Md']")
				.stream().map(foundLinksList -> foundLinksList.getText())
				.collect(Collectors.toList());
	}	

	public List<String> getSearchResultsLinksList() {
		return WebElementUtil.getListOfElements("//div[@class='yuRUbf']/a")
				.stream()
				.map(foundLinksList -> foundLinksList.getAttribute("href"))
				.collect(Collectors.toList());
	}
	
public GoogleSearchResultsImagesPage navigateToImagePage() {
		WebElementUtil.clickElement("//*[@data-hveid='CAEQAw']");		
		
		return new GoogleSearchResultsImagesPage();
	}		

	public GoogleSearchResultsTimeSortPage sortBy(String filter_criteria) {
		WebElementUtil.clickElement("//div[@id='hdtb-tls']");

		WebElementUtil.clickElement("//div[@id='hdtbMenus']/span[1]");

		WebElementUtil.clickElement("//a[contains(text(), '" + filter_criteria + "')]");

		return new GoogleSearchResultsTimeSortPage();
	}
}

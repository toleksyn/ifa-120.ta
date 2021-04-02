package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class GoogleSearchResultsPage {

	public WebElement getSearchResultsLinksText(int elementIndex) {
		return WebElementUtil.getElementFromListByIndex("//h3[@class='LC20lb DKV0Md']", elementIndex+1);
	}	

	public List<String> getSearchResultsLinksList() {
		return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
				.stream()
				.map(foundLinksList -> foundLinksList.getAttribute("href"))
				.collect(Collectors.toList());
	}
	
public GoogleSearchResultsImagesPage navigateToImagePage() {
		WebElementUtil.clickElement("//*[@data-hveid='CAEQAw']");		
		
		return new GoogleSearchResultsImagesPage();
	}		

	public GoogleSearchSortResultsPage sortBy(String sortFilter) {
		WebElementUtil.clickElement("//div[@id='hdtb-tls']");

		WebElementUtil.clickElement("//div[@id='hdtbMenus']/span[1]");

		WebElementUtil.clickElement("//a[contains(text(), '" + sortFilter + "')]");

		return new GoogleSearchSortResultsPage();
	}
}

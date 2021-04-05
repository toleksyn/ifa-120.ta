package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class GoogleSearchResultsPage {

	public WebElement getSearchResultsLinksText(int elementIndex) {
		return WebElementUtil.getElementByIndex("//h3[@class='LC20lb DKV0Md']", elementIndex);
	}

	public List<String> getSearchResultsLinksList() {
		return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a").stream()
				.map(foundLinksList -> foundLinksList.getAttribute("href"))
				.collect(Collectors.toList());
	}

	public GoogleSearchResultsImagesPage navigateToImagePage() {
		WebElementUtil.clickElement("//*[@data-hveid='CAEQAw']");

		return new GoogleSearchResultsImagesPage();
	}

	public GoogleSearchSortResultsPage sortBy(String sortFilter) {
		WebElementUtil.clickElement("//div[@id='hdtb-tls']");

		WebElementUtil.clickElement("(//div[@class='KTBKoe'])[1]");

		String sortFilterXPath = String.format(String.format("%s'%s')]", "//a[contains(text(),", sortFilter));
		WebElementUtil.clickElement(sortFilterXPath);

		return new GoogleSearchSortResultsPage();
	}
}

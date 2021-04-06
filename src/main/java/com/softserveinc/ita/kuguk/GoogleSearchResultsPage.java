package com.softserveinc.ita.kuguk;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

	public String getSearchResultsLinksText(int elementIndex) {

		return $x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md']", elementIndex + 1))
				.getText();
	}

	public List<String> getSearchResultsLinksList() {
		return $$x("//div[@class='yuRUbf']/a")
				.stream().map(foundLinksList -> foundLinksList
				.getAttribute("href"))
				.collect(Collectors.toList());
	}

	public GoogleSearchResultsImagesPage navigateToImagePage() {
		$x("//*[@data-hveid='CAEQAw']").click();

		return new GoogleSearchResultsImagesPage();
	}

	public GoogleSearchSortResultsPage sortBy(String sortFilter) {
		$x("//div[@id='hdtb-tls']").click();
		$x("(//div[@class='KTBKoe'])[1]").click();

		String sortFilterXPath = String.format(String.format("%s'%s')]", "//a[contains(text(),", sortFilter));
		$x(sortFilterXPath).click();

		return new GoogleSearchSortResultsPage();
	}
}

package com.softserveinc.ita.kuguk;

import java.util.List;
import java.util.stream.Collectors;
import com.softserveinc.ita.common.WebElementUtil;

public class GoogleSearchResultsImagesPage {

	public List<String> getSearchResultsImagesText() {

		return WebElementUtil.getElementsListAtLeast("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
				.stream()
				.map(foundImagesList -> foundImagesList.getAttribute("title"))
				.collect(Collectors.toList());
		}

	public GoogleHomePage gotoGoogleHomePageByLogo() {
		WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");

		return new GoogleHomePage();
	}
}

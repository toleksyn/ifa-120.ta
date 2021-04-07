package com.softserveinc.ita.kuguk;

import com.codeborne.selenide.Selenide;

public class GoogleSearchSortResultsPage {

	public String getSearchTimeSortResultsText(String sortFilter, int elementIndex) {
		return Selenide.$x(String.format("(%s'%s')])[%d]", "//span[contains(text(),", sortFilter, elementIndex + 1))
				.getText();
	}
}
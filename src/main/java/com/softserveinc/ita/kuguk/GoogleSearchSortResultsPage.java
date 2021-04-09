package com.softserveinc.ita.kuguk;

import com.codeborne.selenide.Selenide;

public class GoogleSearchSortResultsPage {

	public String getSearchTimeSortResultsText(String sortFilter, int elementIndex) {
		return Selenide.$x(String.format("(//span[contains(text(),'%s')])[%d]", sortFilter, elementIndex + 1))
				.getText();
	}
}
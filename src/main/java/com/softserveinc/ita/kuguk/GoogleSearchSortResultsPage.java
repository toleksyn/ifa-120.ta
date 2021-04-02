package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

public class GoogleSearchSortResultsPage {

	public String getSearchTimeSortResultsText(String sortFilter, int elementIndex) {
		return WebElementUtil
				.getElementFromListByIndex("//span[contains(text(),'" + sortFilter + "')]", elementIndex)
				.getText();
	}

}
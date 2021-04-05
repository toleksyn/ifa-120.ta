package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

public class GoogleSearchSortResultsPage {

	public String getSearchTimeSortResultsText(String sortFilter, int elementIndex) {
		String sortFilterXPath = String.format(String.format("%s'%s')]", "//span[contains(text(),", sortFilter));

		return WebElementUtil.getElementByIndex(sortFilterXPath, elementIndex).getText();
	}
}
package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.WebElementUtil;

public class GoogleSearchResultsTimeSortPage {

	public String getSearchTimeSortResultsText(String locatorForAssert, int elementIndex) {
		return WebElementUtil
				.getElementFromListByIndex("//span[contains(text(),'" + locatorForAssert + "')]", elementIndex)
				.getText();
	}

}
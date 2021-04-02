package com.softserveinc.ita.kuguk;

import org.openqa.selenium.Keys;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;

public class GoogleHomePage {

	public GoogleHomePage open() {
		TestRunner.getDriver().get("https://www.google.com");
		return this;
	}

	public GoogleSearchResultsPage searchFor(String searchString) {
		WebElementUtil.setElementValue("//input[@name='q']", searchString + Keys.ENTER);

		return new GoogleSearchResultsPage();
	}

}

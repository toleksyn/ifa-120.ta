package com.softserveinc.ita.kuguk;

import org.openqa.selenium.support.ui.ExpectedConditions;
import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;

public class GoogleSettingsPage {
	public GoogleHomePage changeGoogleLanguage(String languageMark) {
		WebElementUtil.clickElement("//a [@href='#languages']");
		WebElementUtil.clickElement("//div [@data-value='" + languageMark + "']");
		WebElementUtil.clickElement("//div [@class='goog-inline-block jfk-button jfk-button-action']");

		TestRunner.getWait().until(ExpectedConditions.alertIsPresent()).accept();

		return new GoogleHomePage();
	}

}

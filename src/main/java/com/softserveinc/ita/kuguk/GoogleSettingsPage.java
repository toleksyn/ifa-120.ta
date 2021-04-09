package com.softserveinc.ita.kuguk;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.Selenide;


public class GoogleSettingsPage {
	public GoogleHomePage changeGoogleLanguage(String languageMark) {
		$x("//a [@href='#languages']").click();;
		$x(String.format("//div [@data-value='%s']", languageMark)).click();
		$x("//div [@class='goog-inline-block jfk-button jfk-button-action']").click();
		Selenide.confirm();
		return new GoogleHomePage();
	}
}

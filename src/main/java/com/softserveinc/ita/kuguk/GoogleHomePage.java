package com.softserveinc.ita.kuguk;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

	public GoogleHomePage open() {
		Selenide.open("https://www.google.com");
		return this;
	}

	public GoogleSearchResultsPage searchFor(String searchString) {
		$x("//input[@name='q']").setValue(searchString).pressEnter();
		return new GoogleSearchResultsPage();
	}

	public GoogleSettingsPage openSettingsPage() {
		$x("//button [@id= 'Mses6b']").click();
		$x("//a[@class='EzVRq']").click();
		return new GoogleSettingsPage();
	}
}

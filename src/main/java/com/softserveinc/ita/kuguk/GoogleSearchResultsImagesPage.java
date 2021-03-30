package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;

import com.softserveinc.ita.common.TestRunner;

public class GoogleSearchResultsImagesPage {

	public String[] getSearchResultsImagesText() {		
		return TestRunner.getDriver().findElements(By.xpath("//*[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
				.stream()
				.map(foundImagesList -> foundImagesList.getAttribute("title"))
				.toArray(String[]::new);
	}

	public GoogleHomePage gotoGoogleHomePageByLogo() {
		TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
		return new GoogleHomePage();
	}
}

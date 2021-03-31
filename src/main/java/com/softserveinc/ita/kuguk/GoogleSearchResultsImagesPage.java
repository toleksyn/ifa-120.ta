package com.softserveinc.ita.kuguk;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

public class GoogleSearchResultsImagesPage {

	public String[] getSearchResultsImagesText() {		
		TestRunner.getWait()
		.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")));
		
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

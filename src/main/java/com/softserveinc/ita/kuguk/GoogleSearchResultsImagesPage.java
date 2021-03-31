package com.softserveinc.ita.kuguk;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.softserveinc.ita.common.TestRunner;

public class GoogleSearchResultsImagesPage {

	public List<String> getSearchResultsImagesText() {
		TestRunner.getWait().until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")));

		return TestRunner.getDriver()
				.findElements(By.xpath("//*[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
				.stream().map(foundImagesList -> foundImagesList.getText())
				.collect(Collectors.toList());
	}

	public GoogleHomePage gotoGoogleHomePageByLogo() {
		TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
		return new GoogleHomePage();
	}
}

package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesPage {
    public GoogleHomePage navigateToHomePage() {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='F1hUFe jbTlie']")))
                .click();
        return new GoogleHomePage();
    }

    public List<String> getListOfSearchResultImagesText() {
        return TestRunner
                .getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")))
                .stream()
                .map(webElement -> webElement.getAttribute("title")).collect(Collectors.toList());
    }
}
package com.softserveinc.ita.sbendus.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(WebElement -> WebElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        WebElement googleLogo = TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']"));
        googleLogo.click();
        return new GoogleHomePage();
    }
}



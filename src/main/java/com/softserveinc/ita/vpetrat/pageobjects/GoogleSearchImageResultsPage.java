package com.softserveinc.ita.vpetrat.pageobjects;

import com.softserveinc.ita.pageobjects_task.vpetrat.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;
public class GoogleSearchImageResultsPage {
    public GoogleHomePage returnToHomePage() {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='F1hUFe jbTlie']")))
                .click();
        return new GoogleHomePage();
    }

    public List<String> getListOfSearchResultImagesText() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@id='islmp']//div[@jsaction='IE7JUb:e5gl8b;dtRDof:s370ud;R3mad:ZCNXMe;v03O1c:cJhY7b;']//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(webElement -> webElement.getAttribute("title")).collect(Collectors.toList());
    }
}

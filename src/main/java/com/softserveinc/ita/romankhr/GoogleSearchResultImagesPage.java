package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultImagesPage {

    public List<String> getSearchResultImagesTitles() {
        return TestRunner.getDriver().findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(webElement -> webElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
        return new GoogleHomePage();
    }
}

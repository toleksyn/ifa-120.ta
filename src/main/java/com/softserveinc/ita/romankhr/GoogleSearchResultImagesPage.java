package com.softserveinc.ita.romankhr;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultImagesPage {

    public List<String> getSearchResultImages() {
        return TestRunner.threadLocalDriver.get().findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(webElement -> webElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage moveToGoogleHomePage() {
        TestRunner.threadLocalDriver.get().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
        return new GoogleHomePage();
    }
}

package com.softserveinc.ita.shladkyi;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesResultPage {

    public List<String> getListOfSearchResultImagesText() {
        return TestRunner.driver.findElements(By.xpath("//img[@class='rg_i Q4LuWd']"))
                .stream()
                .map(webElement -> webElement.getAttribute("alt"))
                .collect(Collectors.toList());
    }


    public GoogleHomePage openGoogleHomePage() {
        TestRunner.driver.findElement(By.cssSelector("[class='TYpZOd']")).click();
        return new GoogleHomePage();
    }
}

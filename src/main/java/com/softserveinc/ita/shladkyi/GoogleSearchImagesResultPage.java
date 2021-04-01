package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesResultPage {

    public List<String> getSearchResultImagesTextList() {
        return TestRunner.getDriver().findElements(By.xpath("//img[@class='rg_i Q4LuWd']"))
                .stream()
                .map(webElement -> webElement.getAttribute("alt"))
                .collect(Collectors.toList());
    }


    public GoogleHomePage openGoogleHomePage() {
        TestRunner.getDriver().findElement(By.cssSelector("[class='TYpZOd']")).click();
        return new GoogleHomePage();
    }
}

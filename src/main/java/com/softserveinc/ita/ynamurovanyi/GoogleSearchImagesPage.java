package com.softserveinc.ita.ynamurovanyi;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesPage {

    public List<String> getSearchResultsLinksText() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//a[@class='F1hUFe jbTlie']"))
                .click();
        return new GoogleHomePage();
    }
}

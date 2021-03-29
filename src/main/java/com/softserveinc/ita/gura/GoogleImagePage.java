package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleImagePage {
    public GoogleImagePage() {}

    public GoogleImagePage(String link) {
        TestRunner.driver.get(link);
    }

    public List<String> getSearchResultsLinksText() {
        return TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getAttribute("title"))
                .limit(10)
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//a[@class='TYpZOd']"))
                .click();
        return new GoogleHomePage();
    }

}

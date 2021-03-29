package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {

    public String getLinkText(int index) {
        return TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList()).get(index);
    }

    public List<String> getResultsLinks() {
        return TestRunner
                .driver
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public ImagesResultsPage navigateToImageResultsPage() {
        TestRunner.driver.findElement(By.xpath("//*[@data-hveid='CAEQAw']")).click();
        return new ImagesResultsPage();
    }
}
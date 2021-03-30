package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {

    public String getLinkText(int index) {
        return TestRunner.getDriver().findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList()).get(index);
    }

    public List<String> getResultsLinks() {
        return TestRunner
                .getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public ImagesResultsPage navigateToImageResultsPage() {
        TestRunner.getDriver().findElement(By.xpath("//*[@data-hveid='CAEQAw']")).click();
        return new ImagesResultsPage();
    }

    public BooksResultsPage navigateToBooksResultsPage() {
        TestRunner.getDriver().findElement(By.xpath("//div[@class='GOE98c']")).click();
        TestRunner.getDriver().findElement(By.xpath("//div[@class='gTMtLb fp-nh']//a[contains(text(),'Books')]")).click();
        return new BooksResultsPage();
    }
}
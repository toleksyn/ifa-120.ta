package com.softserveinc.ita.kuguk;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public List<String> getSearchResultsLinksText() {
        return TestRunner.getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .stream()
                .map(foundLinksList -> foundLinksList.getText())
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinksList() {
        return TestRunner.getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yuRUbf']/a")))
                .stream()
                .map(foundLinksList -> foundLinksList.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchResultsImagesPage navigateToImagePage() {
        TestRunner.getWait()
                .until(ExpectedConditions.elementToBeClickable(TestRunner.getDriver().findElement(By.xpath("//*[@data-hveid='CAEQAw']"))))
                .click();
        return new GoogleSearchResultsImagesPage();
    }
}

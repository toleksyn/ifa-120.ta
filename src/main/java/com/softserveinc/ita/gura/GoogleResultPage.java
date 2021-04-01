package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleResultPage {
    public GoogleImagePage goToImagePage() {
        WebElement imagesBottom = TestRunner.getDriver().findElement(By.xpath("//a[@data-hveid='CAEQAw']"));
        return new GoogleImagePage();
    }

    public String getTextFromLink(int indexOfLink) {
        return Stream.of(TestRunner.getDriver()
                .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(element -> element
                        .get(indexOfLink)
                        .getText())
                .map(String::toString)
                .collect(Collectors.joining());
    }

    public List<String> getListOfSearchResultLinks() {
        return TestRunner
                .getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yuRUbf']/a")))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

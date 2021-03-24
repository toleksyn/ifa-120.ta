package com.softserveinc.ita.vPetrat.pageObjects;

import com.softserveinc.ita.pageobjects_task.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {

    public List<String> getListOfSearchResultLinks() {
        return TestRunner
                .getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yuRUbf']/a")))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public String getSearchResultLinkForIndex(int index) {
        return getListOfSearchResultLinks().get(index);
    }
}

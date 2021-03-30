package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultWebdriverPage {

    public List<String> getSearchResultsDescription() {
        return TestRunner.getDriver().findElements(By.xpath("//div[@class='IsZvec']"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}

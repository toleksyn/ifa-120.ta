package com.softserveinc.ita.nromanchuk;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

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
}

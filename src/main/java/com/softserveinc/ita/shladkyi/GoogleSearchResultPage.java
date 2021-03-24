package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.pageobjects_task.shladkyi.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {
    public List<String> getSearchResultLink(int indexOfLink) {
        return Stream.of(TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(webElements -> webElements.get(indexOfLink).getText())
                .collect(Collectors.toList());
    }
}

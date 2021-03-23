package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.pageobjects_task.ynamurovanyi.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultsPage {
    public static List<String> getSearchResultsLinks() {
        return Stream.of(TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(searchResultsLinksText -> searchResultsLinksText.get(0).getText())
                .collect(Collectors.toList());

    }
}

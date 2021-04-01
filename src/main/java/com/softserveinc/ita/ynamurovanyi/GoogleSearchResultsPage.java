package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage {

    public List<String> getSearchResultsLinksText() {
        return TestRunner.getDriver()
                .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"))
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public List<String> getSearchResultsLinks() {
        return TestRunner.getDriver()
                .findElements(By.xpath("//div[@class='yuRUbf']/a"))
                .stream()
                .map(searchResultsLinks -> searchResultsLinks.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesPage navigateToImagesResultsPage() {
        TestRunner.getDriver()
                .findElement(By.xpath("//a[@class='hide-focus-ring']"))
                .click();
        return new GoogleSearchImagesPage();
    }

    public GoogleSearchResultsPage navigateToResultsPageNumber(int targetPageNumber) {
        int currentPageNumber = getSearchResultsPageNumber();
        int targetIndex = -1;
        int minIndex = -1;
        int maxIndex = -1;
        List<String> pageNavigationButtonsValue = TestRunner.threadLocalDriver
                .get()
                .findElements(By.xpath("//tr//a"))
                .stream()
                .map(buttonsValue -> buttonsValue.getAttribute("aria-label"))
                .collect(Collectors.toList());
        if (currentPageNumber != targetPageNumber) {
            for (int i = 0; i < pageNavigationButtonsValue.size(); i++) {
                if (pageNavigationButtonsValue.get(i) != null) {
                    int currentValue = Integer.parseInt(pageNavigationButtonsValue.get(i).replaceAll("[^0-9]", ""));
                    if (currentValue == targetPageNumber) {
                        targetIndex = i;
                    } else {
                        if (minIndex < 0) {
                            minIndex = i;
                        }
                        if (maxIndex < i) {
                            maxIndex = i;
                        }
                    }
                }
            }
        }
        if (targetIndex > -1) {
            TestRunner.threadLocalDriver
                    .get()
                    .findElements(By.xpath("//tr//a"))
                    .get(targetIndex)
                    .click();
        } else {
            if (currentPageNumber < targetPageNumber
                    && pageNavigationButtonsValue.get((pageNavigationButtonsValue.size() - 1)) == null) {
                TestRunner.threadLocalDriver
                        .get()
                        .findElements(By.xpath("//tr//a"))
                        .get(maxIndex)
                        .click();
                navigateToResultsPageNumber(targetPageNumber);
            }
            if (currentPageNumber > targetPageNumber) {
                TestRunner.threadLocalDriver
                        .get()
                        .findElements(By.xpath("//tr//a"))
                        .get(minIndex)
                        .click();
                navigateToResultsPageNumber(targetPageNumber);
            }
        }
        return new GoogleSearchResultsPage();
    }

    public int getSearchResultsPageNumber() {
        String pageNumber = TestRunner.threadLocalDriver
                .get()
                .findElement(By.xpath("//div[@id='result-stats']"))
                .getText();
        pageNumber = pageNumber.substring(0, pageNumber.indexOf(":"));  //replace ":" with "f" for EN lang
        pageNumber = pageNumber.replaceAll("[^0-9]", "");
        if (pageNumber.isEmpty()) {
            pageNumber = "1";
        }
        return Integer.valueOf(pageNumber);
    }
}


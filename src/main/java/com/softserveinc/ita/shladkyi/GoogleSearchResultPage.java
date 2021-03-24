package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.pageobjects_task.shladkyi.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {
    public List<String> getOneSearchResultLink(int indexOfLink) {
        return Stream.of(TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(webElements -> webElements.get(indexOfLink).getText())
                .collect(Collectors.toList());
    }

    public List<String> getAllSearchResultLinks() {
        List<WebElement> allSearchResultLinks = TestRunner.driver.findElements(By.xpath("//div[@class='TbwUpd NJjxre']"));
        return allSearchResultLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}

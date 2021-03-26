package com.softserveinc.ita.shladkyi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {
    public String getSearchResultLinkText(int indexOfLink) {
        List<WebElement> allSearchResultLinks = TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"));
        return allSearchResultLinks.get(indexOfLink).getText();
    }

    public List<String> getAllSearchResultLinks() {
        List<WebElement> allSearchResultLinks = TestRunner.driver.findElements(By.xpath("//div[@class='TbwUpd NJjxre']"));
        return allSearchResultLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}

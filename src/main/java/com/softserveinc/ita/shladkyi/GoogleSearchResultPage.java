package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.pageobjects_task.shladkyi.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {
    public String getFirstSearchResultLink() {
        return TestRunner.driver.findElement(By.cssSelector("[class='LC20lb DKV0Md']")).getText();
    }

    public List<String> getAllSearchResultLinks() {
        List<WebElement> allSearchResultLinks = TestRunner.driver.findElements(By.xpath("//div[@class='TbwUpd NJjxre']"));
        return allSearchResultLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}

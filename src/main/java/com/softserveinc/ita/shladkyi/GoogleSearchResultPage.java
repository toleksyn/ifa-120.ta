package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {
    public String getSearchResultLinkText(int indexOfLink) {
        return WebElementUtil.getElementFromListForIndex("//h3[@class='LC20lb DKV0Md']", indexOfLink)
                .getText();
    }

    public List<String> getAllSearchResultLinks() {
        return WebElementUtil.getListOfElements("//div[@class='TbwUpd NJjxre']")
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesResultPage openImagesPage() {
        WebElementUtil.clickElement("//a[@class='hide-focus-ring']");
        return new GoogleSearchImagesResultPage();
    }
}

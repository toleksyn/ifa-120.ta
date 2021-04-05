package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultPage {
    public String getSearchResultLinkText(int indexOfLink) {
        return WebElementUtil.getElementByIndex("//h3[@class='LC20lb DKV0Md']", indexOfLink)
                .getText();
    }

    public List<String> getAllSearchResultLinks() {
        return WebElementUtil.getElementsList("//div[@class='TbwUpd NJjxre']")
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesResultPage openImagesPage() {
        WebElementUtil.clickElement("//a[@class='hide-focus-ring']");
        return new GoogleSearchImagesResultPage();
    }
}

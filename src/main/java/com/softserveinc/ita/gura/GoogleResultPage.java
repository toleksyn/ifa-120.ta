package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleResultPage {
    public String getTextFromLink(int indexOfLink) {
        return WebElementUtil.getElementFromListByIndex("//h3[@class='LC20lb DKV0Md'][1]", indexOfLink).getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return WebElementUtil.getListOfElements("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

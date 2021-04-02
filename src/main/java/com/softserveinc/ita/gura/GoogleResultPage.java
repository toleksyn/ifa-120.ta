package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.WebElementUtil;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleResultPage {
    public String getTextFromLink(int indexOfLink) {
        return WebElementUtil.getElementFromListByIndex("//h3[@class='LC20lb DKV0Md'][1]", indexOfLink).getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return WebElementUtil.getElementsList("//div[@class='yuRUbf']/a")
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public GoogleImagePage goToImagePage() {
        WebElementUtil.clickElement("//a[@data-hveid='CAEQAw']");
        return new GoogleImagePage();
    }
}

package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesResultPage {

    public List<String> getSearchResultImagesTextList() {
        return WebElementUtil.getListOfElementsMoreThenAmount("//img[@class='rg_i Q4LuWd']", 10)
                .stream()
                .map(webElement -> webElement.getAttribute("alt"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage openGoogleHomePage() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}

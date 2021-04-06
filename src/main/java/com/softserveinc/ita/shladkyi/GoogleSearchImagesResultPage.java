package com.softserveinc.ita.shladkyi;


import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchImagesResultPage {

    public List<String> getSearchResultImagesTextList(int amount) {
//        return WebElementUtil.getElementsListAtLeast("//img[@class='rg_i Q4LuWd']", 10)
//                .stream()
//                .map(webElement -> webElement.getAttribute("alt"))
//                .collect(Collectors.toList());
        return $$x("//img[@class='rg_i Q4LuWd']").shouldHaveSize(amount)
                .stream()
                .map(selenideElement -> selenideElement.getAttribute("alt"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage openGoogleHomePage() {
//        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        $x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}

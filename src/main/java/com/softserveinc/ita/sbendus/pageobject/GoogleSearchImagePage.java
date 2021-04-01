package com.softserveinc.ita.sbendus.pageobject;

import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.ita.common.WebElementUtil.clickElement;
import static com.softserveinc.ita.common.WebElementUtil.getListOfElementsMoreThenAmount;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults() {
        return getListOfElementsMoreThenAmount("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(WebElement -> WebElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}



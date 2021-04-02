package com.softserveinc.ita.sbendus.pageobject;

import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.ita.common.WebElementUtil.clickElement;
import static com.softserveinc.ita.common.WebElementUtil.getElementsListAtLeast;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults() {
        return getElementsListAtLeast("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(WebElement -> WebElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}



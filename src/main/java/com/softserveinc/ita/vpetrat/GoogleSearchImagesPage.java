package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesPage {
    public GoogleHomePage navigateToHomePage() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }

    public List<String> getListOfSearchResultImagesText() {
        return WebElementUtil.getListOfElementsMoreThenAmount("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(webElement -> webElement.getAttribute("title")).collect(Collectors.toList());
    }
}
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
         return WebElementUtil.getListOfElements("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .stream()
                .map(webElement -> webElement.getAttribute("title")).collect(Collectors.toList());
    }
}
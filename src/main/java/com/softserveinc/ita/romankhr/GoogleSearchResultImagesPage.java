package com.softserveinc.ita.romankhr;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultImagesPage {

    public List<String> getSearchResultImagesTitles() {
        return WebElementUtil.getElementsList("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .stream()
                .map(webElement -> webElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}

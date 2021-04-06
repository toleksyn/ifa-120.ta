package com.softserveinc.ita.ynamurovanyi;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesPage {

    public List<String> getSearchResultsLinksText() {
        return WebElementUtil.getElementsListAtLeast("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(searchResultsLinksText -> searchResultsLinksText.getText())
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}

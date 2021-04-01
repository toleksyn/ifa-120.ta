package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.WebElementUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleImagePage {
    public List<String> getImagesTitles() {
        return WebElementUtil.getListOfElementsMoreThenAmount("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(linksText -> linksText.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage goToHomePageByLogo() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}

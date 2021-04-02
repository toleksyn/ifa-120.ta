package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleImagePage {
    public List<String> getImagesTitles() {
        return WebElementUtil.getElementsListAtLeast("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']", 10)
                .stream()
                .map(linksText -> linksText.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage goToHomePageByLogo() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }

    public String getGoogleLogoLink() {
        return WebElementUtil.getElement("//a[@class='F1hUFe jbTlie']").getAttribute("href");
    }
}

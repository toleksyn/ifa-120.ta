package com.softserveinc.ita.nromanchuk;

import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ImagesResultsPage {

    public List<WebElement> getListOfTextResults() {
        return WebElementUtil.getListOfElements("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']");
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        WebElementUtil.clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }

}

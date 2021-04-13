package com.softserveinc.ita.deprecated.ynamurovanyi;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

import java.util.List;

public class GoogleSearchImagesPage {

    public List<String> getSearchResultsLinksText() {
        return Selenide.$$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .shouldHave(CollectionCondition.sizeGreaterThan(9))
                .texts();
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        Selenide.$x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}

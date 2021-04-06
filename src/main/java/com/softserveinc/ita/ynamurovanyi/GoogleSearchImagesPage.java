package com.softserveinc.ita.ynamurovanyi;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

import java.util.List;

public class GoogleSearchImagesPage {

    public List<String> getSearchResultsLinksText() {
        return Selenide.$$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .should(CollectionCondition.sizeGreaterThan(9))
                .texts();
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        Selenide.$x("//div[@class='ZbYMvd']").click();
        Selenide.$x("//div[@class='logo doodle']").click();
        return new GoogleHomePage();
    }
}

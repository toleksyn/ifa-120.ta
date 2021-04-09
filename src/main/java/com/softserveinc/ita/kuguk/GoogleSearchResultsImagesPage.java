package com.softserveinc.ita.kuguk;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultsImagesPage {

    public List<String> getSearchResultsImagesText() {
        return $$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']").texts();
    }

    public GoogleHomePage gotoGoogleHomePageByLogo() {
        $x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}

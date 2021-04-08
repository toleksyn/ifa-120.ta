package com.softserveinc.ita.nromanchuk;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

public class ImagesResultsPage {

    public ElementsCollection getListOfTextResults() {
        return $$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']");
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        $x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }

}

package com.softserveinc.ita.deprecated.romankhr;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultImagesPage {

    public List<String> getSearchResultImagesTitles() {
        return $$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .stream()
                .map(webElement -> webElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        $x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}

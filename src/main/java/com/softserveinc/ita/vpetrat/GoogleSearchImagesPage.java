package com.softserveinc.ita.vpetrat;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagesPage {
    public GoogleHomePage navigateToHomePage() {
        Selenide.$x("//div[@class='ZbYMvd']").click();
        return new GoogleHomePage();
    }

    public List<String> getListOfSearchResultImagesText(int amount) {
        return Selenide.$$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .shouldHave(CollectionCondition.sizeGreaterThan(amount))
                .stream()
                .map(selenideElement -> selenideElement.getAttribute("title")).collect(Collectors.toList());
    }
}
package com.softserveinc.ita.shladkyi;


import com.codeborne.selenide.CollectionCondition;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchImagesResultPage {

    public List<String> getSearchResultImagesTextList(int amount) {
        return $$x("//img[@class='rg_i Q4LuWd']")
                .should(CollectionCondition.sizeGreaterThan(amount))
                .stream()
                .map(selenideElement -> selenideElement.getAttribute("alt"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage openGoogleHomePage() {
        $x("//div[@class='ZbYMvd']").click();
        return new GoogleHomePage();
    }
}

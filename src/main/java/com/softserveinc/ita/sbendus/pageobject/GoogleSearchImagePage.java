package com.softserveinc.ita.sbendus.pageobject;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults(int amount) {
        return Selenide.$$x("//h3[@class='LC20lb DKV0Md']")
                .should(CollectionCondition.sizeGreaterThan(amount))
                .stream()
                .map(selenideElement -> selenideElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        Selenide.$x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }
}



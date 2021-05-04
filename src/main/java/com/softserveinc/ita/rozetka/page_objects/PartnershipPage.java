package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class PartnershipPage {
    public String getHeaderText() {
        return $x("//h1").text();
    }

    public String getPartnershipArticleText() {
        return $x("//article[contains(@class, 'text')]").text();
    }
}

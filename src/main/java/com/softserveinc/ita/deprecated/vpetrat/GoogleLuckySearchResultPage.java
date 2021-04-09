package com.softserveinc.ita.deprecated.vpetrat;

import com.codeborne.selenide.WebDriverRunner;

public class GoogleLuckySearchResultPage {
    public String getPageUrl() {
        return WebDriverRunner.url();
    }
}

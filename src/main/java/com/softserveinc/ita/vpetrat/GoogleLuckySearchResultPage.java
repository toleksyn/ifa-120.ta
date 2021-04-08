package com.softserveinc.ita.vpetrat;

import com.codeborne.selenide.WebDriverRunner;

public class GoogleLuckySearchResultPage {
    public String getPageUrl() {
        return WebDriverRunner.url();
    }
}

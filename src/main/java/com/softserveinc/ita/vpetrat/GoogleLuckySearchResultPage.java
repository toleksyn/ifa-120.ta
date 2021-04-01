package com.softserveinc.ita.vpetrat;

import com.softserveinc.ita.common.TestRunner;

public class GoogleLuckySearchResultPage {
    public String getPageUrl() {
        return TestRunner.getDriver().getCurrentUrl();
    }
}

package com.softserveinc.ita.common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public class TestRunner {

    @BeforeSuite
    public void setupWebDriverTypeAndProperties() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 60000;
        Configuration.pollingInterval = 2000;
    }
}
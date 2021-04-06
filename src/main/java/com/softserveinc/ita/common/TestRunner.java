package com.softserveinc.ita.common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public class TestRunner {
<<<<<<< HEAD
    @BeforeSuite
    public void setupWebDriverTypeAndProperties() {
        Configuration.browser = "chrome";
        Configuration.timeout = 60000;
        Configuration.startMaximized = true;
=======

    @BeforeSuite
    public void setupWebDriverTypeAndProperties() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 60000;
>>>>>>> origin/CommonBranch
        Configuration.pollingInterval = 2000;
    }
}


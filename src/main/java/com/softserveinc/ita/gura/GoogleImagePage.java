package com.softserveinc.ita.gura;

import org.openqa.selenium.WebDriver;

public class GoogleImagePage {
    public static WebDriver getDriver() {
        return TestRunner.driver;
    }

    public GoogleImagePage(String link) {
        TestRunner.driver.get(link);
    }
}

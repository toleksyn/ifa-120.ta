package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleResultPage {
    public static WebDriver getDriver() {
        return TestRunner.driver;
    }

    public GoogleImagePage goToImagePage() {
        WebElement imagesBottom = getDriver().findElement(By.xpath("//a[@data-hveid='CAEQAw']"));
        return new GoogleImagePage(imagesBottom.getAttribute("href"));
    }
}

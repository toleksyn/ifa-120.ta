package com.softserveinc.ita.shladkyi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSettingsPage {
    public GoogleHomePage changeGoogleLanguage(String abbreviationLanguage) {
        TestRunner.driver.findElement(By.id("langSecLink")).click();
        TestRunner.driver.findElement(By.id("langanchormore")).click();
        TestRunner.driver.findElement(By.cssSelector("[data-value='" + abbreviationLanguage + "']")).click();
        TestRunner.driver.findElement(By.cssSelector("[class='goog-inline-block jfk-button jfk-button-action']")).click();
        Alert alertConfirmChanges = TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        alertConfirmChanges.accept();
        return new GoogleHomePage();
    }


}

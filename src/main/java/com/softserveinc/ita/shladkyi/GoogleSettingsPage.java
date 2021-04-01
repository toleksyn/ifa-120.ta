package com.softserveinc.ita.shladkyi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.softserveinc.ita.common.TestRunner;

public class GoogleSettingsPage {
    public GoogleHomePage changeGoogleLanguage(String abbreviationLanguage) {
        TestRunner.getDriver().findElement(By.id("langSecLink")).click();
        TestRunner.getDriver().findElement(By.id("langanchormore")).click();
        TestRunner.getDriver().findElement(By.cssSelector("[data-value='" + abbreviationLanguage + "']")).click();
        TestRunner.getDriver().findElement(By.cssSelector("[class='goog-inline-block jfk-button jfk-button-action']")).click();
        Alert alertConfirmChanges = TestRunner.getWait().until(ExpectedConditions.alertIsPresent());
        alertConfirmChanges.accept();
        return new GoogleHomePage();
    }


}

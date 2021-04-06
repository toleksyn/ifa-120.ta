package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

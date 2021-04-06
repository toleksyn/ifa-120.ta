package com.softserveinc.ita.shladkyi;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.common.TestRunner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSettingsPage {
    public GoogleHomePage changeGoogleLanguage(String abbreviationLanguage) {
//        WebElementUtil.clickElement("//li[@id='langSecLink']");
        $x("//li[@id='langSecLink']").click();
//        WebElementUtil.clickElement("//a[@id='langanchormore']");
        $x("//a[@id='langanchormore']").click();
//        WebElementUtil.clickElement("//div[@data-value='" + abbreviationLanguage + "']");
        $x(String.format("//div[@data-value='%s']", abbreviationLanguage)).click();
//        WebElementUtil.clickElement("//div[@class='goog-inline-block jfk-button jfk-button-action']");
        $x("//div[@class='goog-inline-block jfk-button jfk-button-action']").click();
//        Alert alertConfirmChanges = TestRunner.getWait().until(ExpectedConditions.alertIsPresent());
//        alertConfirmChanges.accept();
        switchTo().alert().accept();
        return new GoogleHomePage();
    }


}

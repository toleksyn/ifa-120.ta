package com.softserveinc.ita.shladkyi;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleSettingsPage {
    public GoogleHomePage changeGoogleLanguage(String abbreviationLanguage) {
        WebElementUtil.clickElement("//li[@id='langSecLink']");
        WebElementUtil.clickElement("//a[@id='langanchormore']");
        WebElementUtil.clickElement("//div[@data-value='" + abbreviationLanguage + "']");
        WebElementUtil.clickElement("//div[@class='goog-inline-block jfk-button jfk-button-action']");
        Alert alertConfirmChanges = TestRunner.getWait().until(ExpectedConditions.alertIsPresent());
        alertConfirmChanges.accept();
        return new GoogleHomePage();
    }


}

package com.softserveinc.ita.shladkyi;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Selenide.*;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;


public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
//        TestRunner.getDriver().get("https://www.google.com/");
        open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
//        WebElementUtil.setElementValue("//input[@class='gLFyf gsfi']", searchTerm + Keys.ENTER);
        $x("//input[@class='gLFyf gsfi']").setValue(searchTerm).pressEnter();
        return new GoogleSearchResultPage();
    }

    public GoogleSettingsPage openSettingsPage() {
//        WebElementUtil.clickElement("//button[@id='Mses6b']");
        $x("//button[@id='Mses6b']").click();
//        WebElementUtil.clickElement("//a[@class='EzVRq']");
        $x("//a[@class='EzVRq']").click();
        return new GoogleSettingsPage();
    }

    public String getSearchButtonText() {
//        return WebElementUtil.getElement("(//input[@class='gNO89b'])[2]").getAttribute("value");
        return $x("(//input[@class='gNO89b'])[2]").getAttribute("value");
    }

    public String getLuckyButtonText() {
//        return WebElementUtil.getElement("(//input[@class='RNmpXc'])[2]").getAttribute("value");
        return $x("(//input[@class='RNmpXc'])[2]").getAttribute("value");
    }
}

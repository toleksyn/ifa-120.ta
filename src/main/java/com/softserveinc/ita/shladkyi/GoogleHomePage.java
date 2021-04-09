package com.softserveinc.ita.shladkyi;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class GoogleHomePage {

    public GoogleHomePage openHomePage() {
        open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchFor(String searchTerm) {
        $x("//input[@class='gLFyf gsfi']").setValue(searchTerm).pressEnter();
        return new GoogleSearchResultPage();
    }

    public GoogleSettingsPage openSettingsPage() {
        $x("//button[@id='Mses6b']").click();
        $x("//a[@class='EzVRq']").click();
        return new GoogleSettingsPage();
    }

    public String getSearchButtonText() {
        return $x("(//input[@class='gNO89b'])[2]").attr("value");
    }

    public String getLuckyButtonText() {
        return $x("(//input[@class='RNmpXc'])[2]").attr("value");
    }
}

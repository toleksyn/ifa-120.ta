package com.softserveinc.ita.gura;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GoogleHomePage {
    public GoogleHomePage openGoogleHomePage() {
        open("https://www.google.com");
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        $x("//input[@name='q']").setValue(inputText).pressEnter();
        return new GoogleResultPage();
    }

    public void hideGoogleLogo() {
        Selenide.executeJavascript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkGoogleLogoVisibility() {
        return $x("//div[@class='k1zIA kKvsb']").getAttribute("style").contains("hidden");
    }
}

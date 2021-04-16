package com.softserveinc.ita.deprecated.gura;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GoogleHomePage {
    public GoogleHomePage openGoogleHomePage() {
        open("https://www.google.com");
        return this;
    }

    public GoogleResultPage searchFor(String request) {
        $x("//input[@name='q']").setValue(request).pressEnter();
        return new GoogleResultPage();
    }

    public void hideGoogleLogo() {
        Selenide.executeJavaScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean isGoogleLogoHidden() {
        return $x("//div[@class='k1zIA kKvsb']").attr("style").contains("hidden");
    }
}

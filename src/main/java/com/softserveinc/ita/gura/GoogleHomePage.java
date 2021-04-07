package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.JavascriptExecutor;
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
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.getDriver();
        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkGoogleLogoVisibility() {
        return $x("//div[@class='k1zIA kKvsb']").getAttribute("style").contains("hidden");
    }
}

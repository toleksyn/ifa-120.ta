package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class GoogleHomePage {
    public GoogleHomePage openGoogleHomePage() {
        TestRunner.getDriver().get("https://www.google.com");
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        WebElementUtil.setElementValue("//input[@name='q']", inputText + Keys.ENTER);
        return new GoogleResultPage();
    }

    public void hideGoogleLogo() {
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.getDriver();
        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkGoogleLogoVisibility() {
        return TestRunner.getDriver().findElement(By.xpath("//img[@class='lnXdpd']")).getAttribute("style").contains("hidden");
    }
}

package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import com.softserveinc.ita.common.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {
    public GoogleHomePage openGoogleHomePage() {
        TestRunner.getDriver().get("https://www.google.com");
        return this;
    }

    public GoogleResultPage doSearch(String inputText) {
        WebElementUtil.setValueForElement("//input[@name='q']", inputText + Keys.ENTER);
        return new GoogleResultPage();
    }

    public GoogleImagePage goToImagePage() {
        WebElementUtil.clickElement("//a[@data-hveid='CAEQAw']");
        return new GoogleImagePage();
    }

    public boolean isGooglePageOpened(String link) {
        if (TestRunner.getDriver().getCurrentUrl() == link) return true;
        else return false;
    }

    public void hideGoogleLogo() {
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.getDriver();
        js.executeScript("document.getElementsByClassName('lnXdpd')[0].setAttribute('style', 'visibility:hidden')");
    }

    public boolean checkGoogleLogoVisibility() {
        return TestRunner.getDriver().findElement(By.xpath("//img[@class='lnXdpd']")).getAttribute("style").contains("hidden");
    }
}

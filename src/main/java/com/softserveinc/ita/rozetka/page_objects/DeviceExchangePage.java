package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.components.ValueCalculatorSection;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.*;

public class DeviceExchangePage {
    public String getHeaderText() {
        return $x("//*[@class='rz-header']").text();
    }

    public String getExchangeContactsText() {
        return $x("//*[contains(@class, 'rz-section rz-contacts')]").text();
    }

    @Step("DeviceExchangePage: scroll to device cost valuation calculator")
    public ValueCalculatorSection scrollToValueCalculator() {
        IntStream.range(0, 2).forEach(scrollsCount -> actions()    //manipulations to display the frame, in the case of its invisibility
                .sendKeys(Keys.PAGE_DOWN)
                .perform());
        switchTo().frame($x("//iframe[contains(@src, 'https://ti.generalse.com')]"));
        return new ValueCalculatorSection();
    }

    @Step("DeviceExchangePage: exit from device cost valuation calculator")
    public DeviceExchangePage exitValueCalculator() {
        switchTo().defaultContent();
        return new DeviceExchangePage();
    }

    @Step("DeviceExchangePage: open Footer")
    public Footer openFooter() {
        $x("//img[contains(@alt, 'Rozetka Logo')]").click();
        return new Footer();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.Footer;
import com.softserveinc.ita.rozetka.components.ValuationCalc;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DeviceExchangePage {
    public String getHeaderText() {
        return $x("//*[@class='rz-header']").text();
    }

    public String getExchangeContactsText() {
        return $x("//*[contains(@class, 'rz-section rz-contacts')]").text();
    }

    @Step("DeviceExchangePage: open device valuation calculator")
    public ValuationCalc openValuationCalc() {
        return new ValuationCalc();
    }

    @Step("DeviceExchangePage: open Footer")
    public Footer getFooter() {
        $x("//img[contains(@alt, 'Rozetka Logo')]").click();
        return new Footer();
    }
}

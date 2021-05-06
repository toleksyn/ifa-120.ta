package com.softserveinc.ita.rozetka.components;

import org.openqa.selenium.Keys;

import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class ValuationCalc {

    public void setDevice(String deviceType, String brandName, String deviceName) {
        IntStream.range(0, 2).forEach(scrollsCount -> actions()    //manipulations to display the frame, in the case of its invisibility
                .sendKeys(Keys.PAGE_DOWN)
                .perform());
        switchTo().frame($x("//iframe[contains(@src, 'https://ti.generalse.com')]"));
        $x("(//span[@class='select2-selection select2-selection--single'])[1]").click();
        $x("//input[@class='select2-search__field']").val(deviceType).pressEnter();

        $x("(//span[@class='select2-selection select2-selection--single'])[2]").click();
        $x("//input[@class='select2-search__field']").val(brandName).pressEnter();

        $x("(//span[@class='select2-selection select2-selection--single'])[3]").click();
        $x("//input[@class='select2-search__field']").val(deviceName).pressEnter();
    }

    public void setPowerStateOption(String canDeviceBePoweredOn) {
        $x(format("//*[@name='power' and @value='%s']", canDeviceBePoweredOn)).click();
    }

    public void setScreenOperationalOption(String isDeviceScreenFullyOperational) {
        $x(format("//*[@name='screen' and @value='%s']", isDeviceScreenFullyOperational)).click();
    }

    public void setDeviceFunctionsOption(String isAllDeviceFunctionsWork) {
        $x(format("//input[@name='function' and @value='%s']", isAllDeviceFunctionsWork)).click();
    }

    public void setScreenState(String screenState) {
        $x("//div[contains(@class,'question_screen_state')]").click();
        $x(format("//input[ @name='screen_state' and @value='%s']", screenState)).click();
    }

    public void setCoverState(String coverState) {
        $x("//div[contains(@class,'question_cover_state')]").click();
        $x(format("//input[ @name='cover_state' and @value='%s']", coverState)).click();
    }

    public String getExchangeCost() {
        return $x("//*[@class='phone_cost-change_cost']").text();
    }
}

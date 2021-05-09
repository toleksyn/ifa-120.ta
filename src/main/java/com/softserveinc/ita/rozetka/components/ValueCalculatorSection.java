package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.enums.DeviceSurfaceState;
import com.softserveinc.ita.rozetka.enums.ExchangedDeviceType;
import com.softserveinc.ita.rozetka.enums.YesNoAnswer;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ValueCalculatorSection {

    @Step("ValueCalculatorSection: set device type")
    public void setDeviceType(ExchangedDeviceType deviceType) {
        $x("(//span[@class='select2-selection select2-selection--single'])[1]").click();
        $x("//input[@class='select2-search__field']").val(deviceType.getDeviceType()).pressEnter();
    }

    @Step("ValueCalculatorSection: set brand name")
    public void setBrandName(String brandName) {
        $x("(//span[@class='select2-selection select2-selection--single'])[2]").click();
        $x("//input[@class='select2-search__field']").val(brandName).pressEnter();
    }

    @Step("ValueCalculatorSection: set device name")
    public void setDeviceName(String deviceName) {
    $x("(//span[@class='select2-selection select2-selection--single'])[3]").click();
    $x("//input[@class='select2-search__field']").val(deviceName).pressEnter();
}

    @Step("ValueCalculatorSection: set power state option")
    public void setCanDeviceBePoweredOn(YesNoAnswer canDeviceBePoweredOn) {
        $x(format("//*[@name='power' and @value='%s']", canDeviceBePoweredOn.getPartialLocatorYesNo())).click();
    }

    @Step("ValueCalculatorSection: set screen operational option")
    public void setIsDeviceScreenFullyOperational(YesNoAnswer isDeviceScreenFullyOperational) {
        $x(format("//*[@name='screen' and @value='%s']", isDeviceScreenFullyOperational.getPartialLocatorYesNo())).click();
    }

    @Step("ValueCalculatorSection: set device functions option")
    public void setIsAllDeviceFunctionsWork(YesNoAnswer isAllDeviceFunctionsWork) {
        $x(format("//input[@name='function' and @value='%s']", isAllDeviceFunctionsWork.getPartialLocatorYesNo())).click();
    }

    @Step("ValueCalculatorSection: set screen state")
    public void setScreenState(DeviceSurfaceState screenState) {
        $x("//div[contains(@class,'question_screen_state')]").click();
        $x(format("//input[ @name='screen_state' and @value='%s']", screenState.getPartialLocatorSurfaceState())).click();
    }

    @Step("ValueCalculatorSection: set cover state")
    public void setCoverState(DeviceSurfaceState coverState) {
        $x("//div[contains(@class,'question_cover_state')]").click();
        $x(format("//input[ @name='cover_state' and @value='%s']", coverState.getPartialLocatorSurfaceState())).click();
    }

    public String getExchangeCost() {
        return $x("//*[@class='phone_cost-change_cost']").text();
    }
}

package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangedDeviceType {

    SMARTPHONE("Смартфон"),
    TABLET("Планшет"),
    SMART_WATCH("Смарт-годинник");

    private final String deviceType;
}


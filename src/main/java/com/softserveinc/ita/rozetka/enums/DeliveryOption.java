package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryOption {
    ROZETKA_JUSTIN("Самовивіз з мінівідділень Rozetka + Justin"),
    JUSTIN("Самовивіз з JustIn"),
    NOVAPOSHTA("Самовивіз з Нової Пошти");

    private final String partialLocatorDeliveryOption;
}


package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSections {

    ONLY_ON_ROZETKA("Тільки в Розетці"),
    HOT_NEW_PRODUCTS("Гарячі новинки"),
    NOW_IN_DEMAND("Зараз користуються попитом"),
    MOST_DISCUSSED_PRODUCTS("Найбільш обговорювані товари"),
    MOST_ANTICIPATED("Найбільш очікувані"),
    MOST_ADDED_TO_WISHLIST("Найчастіше додають в лист бажань");

    private final String sectionName;
}


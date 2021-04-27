package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageOption {
    RU("RU"),
    UA("UA");

    private final String languageKey;
}

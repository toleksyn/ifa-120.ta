package com.softserveinc.ita.rozetka.page_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageOption {
    RU("RU"),
    UA("UA");

    private final String languageKey;
}

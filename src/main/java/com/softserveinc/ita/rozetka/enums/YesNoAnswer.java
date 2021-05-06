package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoAnswer {
    Yes("1"),
    No("0");

    private final String yesNoAnswer;
}

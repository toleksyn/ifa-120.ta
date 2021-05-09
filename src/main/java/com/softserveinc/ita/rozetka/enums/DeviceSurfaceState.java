package com.softserveinc.ita.rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceSurfaceState {

    NO_SCRATCHES("A"),
    BARELY_NOTICEABLE("B"),
    QUITE_NOTICEABLE("C"),
    STRONG_SCRATCHES("D");

    private final String partialLocatorSurfaceState;
}


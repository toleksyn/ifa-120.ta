package com.softserveinc.ita.rozetka.page_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductPageTab {

    DESCRIPTION("description"),
    CHARACTERISTICS("characteristics"),
    COMMENTS("comments"),
    QUESTIONS("questions"),
    PHOTO("photo");

    private final String tabHrefIdentifier;
}


package com.softserveinc.ita.rozetka.page_objects;

public enum ProductPageTab {

    DESCRIPTION("description"),
    CHARACTERISTICS("characteristics"),
    COMMENTS("comments"),
    QUESTIONS("questions"),
    PHOTO("photo");

    private final String tabHrefIdentifier;

    ProductPageTab(String tabHrefIdentifier) {
        this.tabHrefIdentifier = tabHrefIdentifier;
    }

    public String getTabHrefIdentifier() {
        return tabHrefIdentifier;
    }
}


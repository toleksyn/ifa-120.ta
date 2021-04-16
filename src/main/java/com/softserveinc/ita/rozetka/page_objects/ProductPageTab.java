package com.softserveinc.ita.rozetka.page_objects;

public enum ProductPageTab {

    GENERAL_DESCRIPTION(""),
    CHARACTERISTICS("characteristics/"),
    COMMENTS("comments/"),
    QUESTIONS("questions/"),
    PHOTO("photo/");

    private final String tabPath;

    ProductPageTab(String tabPath) {
        this.tabPath = tabPath;
    }

    public String getTabPath() {
        return tabPath;
    }
}

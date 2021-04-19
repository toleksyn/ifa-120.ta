package com.softserveinc.ita.rozetka.page_objects;

public enum ProductPageTab {

    DESCRIPTION("description"),
    CHARACTERISTICS("characteristics"),
    COMMENTS("comments"),
    QUESTIONS("questions"),
    PHOTO("photo");

    private final String tabName;

    ProductPageTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}


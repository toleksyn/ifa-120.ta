package com.softserveinc.ita.rozetka.page_objects;

public enum ProductPageTab {

    GENERAL_DESCRIPTION(" Усе про товар "),
    CHARACTERISTICS(" Характеристики "),
    REVIEWS(" Відгуки "),
    ADD_REVIEW(" Залишити відгук "),
    QUESTIONS(" Питання "),
    ASK_QUESTION(" Поставити запитання ");

    private String tabName;

    ProductPageTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}

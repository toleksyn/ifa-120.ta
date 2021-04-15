package com.softserveinc.ita.rozetka.page_objects;

public enum LeftSidebarCategories {

    LAPTOPS_AND_COMPUTERS("Ноутбуки та комп’ютери");

    private String categoryName;

    LeftSidebarCategories(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

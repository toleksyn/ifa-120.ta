package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage {

    public HomePage openHomePage() {
        open("https://rozetka.com.ua/ua/");
        return this;
    }

//    public CategoryPage navigateToCategoryPageFromLeftSidebar(int number) {
//        $x(String.format("(//ul[@class='menu-categories menu-categories_type_main']//li[%d])", number)).doubleClick();
//        return new CategoryPage();
//    }

    public CategoryPage navigateToCategoryPageFromLeftSidebar(String categoryName) {
        $x(String.format("//a[@class = 'menu-categories__link js-menu-categories__link' and contains(text(), '%s')]", categoryName))
                .click();
        return new CategoryPage();
    }
}

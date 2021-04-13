package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    public HomePage openHomePage() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public ConcreteCategoryPage navigateToCategoryPage(String pageCategory) {
     $x(String.format("//a[@class = 'menu-categories__link js-menu-categories__link' and contains(text(), '%s')]", pageCategory))
             .click();
        return new ConcreteCategoryPage();
    }
}

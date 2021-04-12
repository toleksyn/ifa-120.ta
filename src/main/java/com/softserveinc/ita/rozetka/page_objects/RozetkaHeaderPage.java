package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RozetkaHeaderPage {
    public RozetkaHeaderPage openHomePage() {
        open("https://rozetka.com.ua/");
        return this;
    }
    public RozetkaSearchResultsPage searchFor(String request) {
        $x("//input[@class='search-form__input ng-untouched ng-pristine ng-valid']").val(request);
        $x("//button[@class='button button_color_green button_size_medium search-form__submit']").click();
        return new RozetkaSearchResultsPage();
    }
}

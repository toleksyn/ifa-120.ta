package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderPage {
    public ProductsListPage searchFor(String request) {
        $x("//input[@class='search-form__input ng-untouched ng-pristine ng-valid']").val(request);
        $x("//button[@class='button button_color_green button_size_medium search-form__submit']").click();
        return new ProductsListPage();
    }
}

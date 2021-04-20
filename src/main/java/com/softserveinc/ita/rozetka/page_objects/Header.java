package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public ProductsListPage searchFor(String request) {
        $x("//input[@class='search-form__input ng-untouched ng-pristine ng-valid']").val(request);
        $x("//button[@class='button button_color_green button_size_medium search-form__submit']").click();
        return new ProductsListPage();
    }

    public HamburgerPage openHamburgerPage() {
        $x("(//button[@class='header__button'])[1]").click();
        return new HamburgerPage();
    }

    public boolean isSearchButtonVisible() {
        return $x("//button[@class='button button_color_green button_size_medium search-form__submit']")
                .isDisplayed();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public ProductsListPage searchFor(String request) {
        $x("//input[contains(@class, 'search-form__input')]").val(request);
        $x("//button[contains(@class, 'search-form__submit')]").click();
        return new ProductsListPage();
    }

    public HamburgerBar openHamburgerBar() {
        $x("(//button[@class='header__button'])[1]").click();
        return new HamburgerBar();
    }

    public String getSearchFieldText() {
        return $x("//input[contains(@class, 'search-form__input')]").attr("placeholder");
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import com.codeborne.selenide.Selenide;

public class RozetkaBasketPage {
    public RozetkaOrderPage goToOrder() {
        Selenide.$x("//a[class='button button_size_large button_color_green cart-receipt__submit']").click();
        return new RozetkaOrderPage();
    }
}

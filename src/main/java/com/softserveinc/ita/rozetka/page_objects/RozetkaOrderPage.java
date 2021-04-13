package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class RozetkaOrderPage {
    public boolean isThisOrderPage() {
        return $x("//h1[class='checkout-heading']").getText() == "Оформлення замовлення";
    }
}

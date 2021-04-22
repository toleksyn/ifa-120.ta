package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class AgeConfirmationPopup {

    public ProductsListPage confirmAdultAge() {
        $x("//a[class='btn-link-i exponea-close']").click();
        return new ProductsListPage();
    }
}

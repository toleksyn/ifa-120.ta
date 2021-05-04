package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsExchangePage {
    public String getHeaderText() {
        return $x("//*[@class='rz-header']").text();
    }

    public String getExchangeContactsText() {
        return $x("//*[contains(@class, 'rz-section rz-contacts')]").text();
    }
}

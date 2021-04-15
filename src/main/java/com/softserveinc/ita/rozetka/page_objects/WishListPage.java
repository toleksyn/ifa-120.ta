package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class WishListPage {

    public String getProductTitleFromWishProductList(String message) {
        return $x(String.format("//span[@class='goods-tile__title'] and contains(text()), '%s'", message))
                .text();
    }
}


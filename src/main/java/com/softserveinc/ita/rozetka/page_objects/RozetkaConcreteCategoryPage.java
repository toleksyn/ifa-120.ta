package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class RozetkaConcreteCategoryPage {

    public RozetkaProductPage navigateToProductByNumber(int number) {
        $x(String.format("(//span[@class='goods-tile__title'])[%d]", 1)).click();
        return new RozetkaProductPage();
    }

}

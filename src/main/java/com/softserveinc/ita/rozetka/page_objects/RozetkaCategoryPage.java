package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class RozetkaCategoryPage {

    public RozetkaCategoryPage navigateToProductCategoryPage(int index) {
        $$x("//a[@class='tile-cats__heading tile-cats__heading_type_center']").get(index).click();
        return new RozetkaCategoryPage();
    }

    public RozetkaProductPage navigateToProductByNumber(int number) {
        $$x("//span[@class='goods-tile__title']").get(number).click();
        return new RozetkaProductPage();
    }

    public RozetkaConcreteCategoryPage navigateToConcreteCategotyPage(int index){
        $x(String.format("(//a[@class='tile-cats__heading tile-cats__heading_type_center'])[%d]", index)).click();
        return new RozetkaConcreteCategoryPage();
    }




}

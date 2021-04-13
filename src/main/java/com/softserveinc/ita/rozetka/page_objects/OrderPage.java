package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    public OrderPage fillAllInputFields(){
        $x("//div[@class='form__row js-surname']/input").setValue("Петренко");
        $x("//div[@class='form__row js-name']/input").setValue("Вася");
        $x("//div[@class='autocomplete']/input").setValue("Івано-Франківськ").click();
        $x("//div[@class='form__row js-phone']/input").setValue("77777777");
        return this;
    }
}

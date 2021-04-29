package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ComparisonPage {
    public ComparisonPage deleteProduct(int productNumber) {
        $x(format("(//button[@class='button button--white button--small context-menu__toggle'])[%d]", productNumber)).click();
        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
        return this;
    }

    public String getAlertMassage() {
        return $x("//div[@class='form__hint form__hint_type_attention ng-star-inserted']").text();
    }
}

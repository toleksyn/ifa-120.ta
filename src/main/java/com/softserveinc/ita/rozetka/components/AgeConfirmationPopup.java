package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.page_objects.ProductsListPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AgeConfirmationPopup {

    @Step("Age confirmation popup: confirm adult age")
    public ProductsListPage confirmAdultAge() {
        $x("//a[class='btn-link-i exponea-close']").click();
        return new ProductsListPage();
    }
}

package com.softserveinc.ita.rozetka.page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class DeliveryCityPage {
    public String getHeaderText() {
        return $x("//h3[@class='modal__heading']").text();
    }

    public DeliveryCityPage closeDeliveryCityPage() {
        $x("//*[@class='modal__close']").click();
        return this;
    }

}

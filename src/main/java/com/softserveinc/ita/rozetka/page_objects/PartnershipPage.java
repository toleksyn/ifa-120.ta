package com.softserveinc.ita.rozetka.page_objects;

import com.softserveinc.ita.rozetka.components.Footer;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PartnershipPage {
    public String getHeaderText() {
        return $x("//h1").text();
    }

    @Step("PartnershipPage: open Footer")
    public Footer openFooter() {
        $x("//img[contains(@alt, 'Rozetka Logo')]").click();
        return new Footer();
    }

    public String getPartnershipArticleText() {
        return $x("//div[contains(@class, 'rz-text')]").text();
    }
}

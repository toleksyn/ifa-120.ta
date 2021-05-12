package com.softserveinc.ita.rozetka.page_objects;

import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;

public class ComparisonPage extends BasePage {
    @Step("Comparison page: show differences")
    public ComparisonPage showDifferences() {
        $x("//button[contains(@class, 'comparison-settings__toggle')]").click();
        return this;
    }

    @Step("Comparison page: delete product")
    public ComparisonPage deleteProduct(int productNumber) {
        $x(format("(//button[@class='button button--white button--small context-menu__toggle'])[%d]", productNumber)).click();
        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
        return this;
    }

    public String getAlertMessage() {
        return $x("//div[@class='form__hint form__hint_type_attention ng-star-inserted']").text();
    }

    @Step("Comparison page: get product characteristic")
    public List<Indexed<String>> getProductCharacteristic(int productNumber) {
        List<String> characteristicList = new ArrayList<>($$x(format("(//dt[contains(@class, 'comparison-characteristic__label')])[%d]", productNumber))
                .texts());
        if (productNumber == 1) {
            return StreamUtils
                    .zipWithIndex(characteristicList.stream())
                    .filter(i -> (i.getIndex() + 1) % 2 != 0)
                    .collect(Collectors.toList());
        } else {
            return StreamUtils
                    .zipWithIndex(characteristicList.stream())
                    .filter(i -> (i.getIndex() + 1) % productNumber == 0)
                    .collect(Collectors.toList());
        }
    }
}

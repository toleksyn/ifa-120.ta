package com.softserveinc.ita.shladkyi;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {
    public String getSearchResultLinkText(int index) {
//        return WebElementUtil.getElementByIndex("//h3[@class='LC20lb DKV0Md']", indexOfLink)
//                .getText();
        return $x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md']", index + 1)).getText();
    }

    public List<String> getAllSearchResultLinks() {
//        return WebElementUtil.getElementsList("//div[@class='TbwUpd NJjxre']")
//                .stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
        return $$x("//div[@class='TbwUpd NJjxre']")
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public GoogleSearchImagesResultPage openImagesPage() {
//        WebElementUtil.clickElement("//a[@class='hide-focus-ring']");
        $x("//a[@class='hide-focus-ring']").click();
        return new GoogleSearchImagesResultPage();
    }
}

package com.softserveinc.ita.shladkyi;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    public String getSearchResultLinkText(int index) {
        return $x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md']", index + 1)).getText();
    }

    public List<String> getAllSearchResultLinks() {
        return $$x("//div[@class='TbwUpd NJjxre']").texts();
    }

    public GoogleSearchImagesResultPage openImagesPage() {
        $x("//a[@class='hide-focus-ring']").click();
        return new GoogleSearchImagesResultPage();
    }
}

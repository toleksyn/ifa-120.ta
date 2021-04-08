package com.softserveinc.ita.gura;

import com.codeborne.selenide.Selenide;
import java.util.List;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleResultPage {
    public String getTextFromLink(int indexOfLink) {
        return Selenide.$x(String.format("(%s)[%d]", "//h3[@class='LC20lb DKV0Md'][1]", indexOfLink)).getText();
    }

    public List<String> getListOfSearchResultLinks() {
        return $$x("//h3[@class='LC20lb DKV0Md']")
                .texts();
    }

    public GoogleImagePage goToImagePage() {
        $x("//a[@data-hveid='CAEQAw']").click();
        return new GoogleImagePage();
    }
}

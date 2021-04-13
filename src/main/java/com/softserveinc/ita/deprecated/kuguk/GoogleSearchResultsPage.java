package com.softserveinc.ita.deprecated.kuguk;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultsPage {

    public String getSearchResultsLinksText(int elementIndex) {
        return $x(String.format("(//h3[@class='LC20lb DKV0Md'])[%d]", elementIndex + 1))
                .getText();
    }

    public List<String> getSearchResultsLinksList() {
        return $$x("//div[@class='yuRUbf']/a").texts();
    }

    public GoogleSearchResultsImagesPage navigateToImagePage() {
        $x("//*[@data-hveid='CAEQAw']").click();
        return new GoogleSearchResultsImagesPage();
    }

    public GoogleSearchSortResultsPage sortBy(String sortFilter) {
        $x("//div[@id='hdtb-tls']").click();
        $x("(//div[@class='KTBKoe'])[1]").click();
        $x(String.format("//a[contains(text(),'%s')]", sortFilter)).click();
        return new GoogleSearchSortResultsPage();
    }
}

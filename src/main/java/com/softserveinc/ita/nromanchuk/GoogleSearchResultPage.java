package com.softserveinc.ita.nromanchuk;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class GoogleSearchResultPage {

    public String getLinkText(int index) {
        return $x(String.format("(%s)[%d]","//h3[@class='LC20lb DKV0Md']", index)).text();
    }

    public List<String> getResultsLinks() {
        return $$x("//div[@class='yuRUbf']/a")
                .stream()
                .map(foundLinksList -> foundLinksList
                        .getAttribute("href"))
                .collect(Collectors.toList());
    }

    public ImagesResultsPage navigateToImageResultsPage() {
        $x("(//a[@class='hide-focus-ring'])[1]").click();
        return new ImagesResultsPage();
    }

    public BooksResultsPage navigateToBooksResultsPage() {
        $x("//div[@class='GOE98c']").click();
        $x(String.format("(%s)[%d]","//div[@class='gTMtLb fp-nh']//a", 2)).click();
        return new BooksResultsPage();
    }
}

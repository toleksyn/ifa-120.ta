package com.softserveinc.ita.deprecated.gura;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleImagePage {
    public List<String> getImagesTitles(int amount) {
        return $$x("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .shouldHaveSize(amount)
                .stream()
                .map(linksText -> linksText.attr("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage goToHomePageByLogo() {
        $x("//a[@class='F1hUFe jbTlie']").click();
        return new GoogleHomePage();
    }

    public String getGoogleLogoLink() {
        return $x("//a[@class='F1hUFe jbTlie']").attr("href");
    }
}

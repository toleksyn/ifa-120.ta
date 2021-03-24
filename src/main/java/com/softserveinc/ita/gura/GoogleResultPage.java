package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.softserveinc.ita.gura.GoogleHomePage.driver;

public class GoogleResultPage {
    public boolean checkLinkHasText(int indexOfLink, String link) {
        if (link.contains(
                Stream.of(driver
                        .findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                        .map(element -> element
                                .get(indexOfLink)
                                .getText())
                        .collect(Collectors
                                .joining()))) return true;
        else return false;
    }
}

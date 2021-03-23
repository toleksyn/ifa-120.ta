package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleSearchResultPage {
    public String getFirstLinkText() {
        return Stream.of(TestRunner.driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']")))
                .map(el -> el.get(0).getText().toLowerCase())
                .collect(Collectors.toList()).get(0);
    }
}

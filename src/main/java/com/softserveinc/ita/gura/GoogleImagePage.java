package com.softserveinc.ita.gura;

import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleImagePage {
    public GoogleImagePage() {}

    public GoogleImagePage(String link) {
        TestRunner.getDriver().get(link);
    }

    public List<String> getImagesTitle() {
        return TestRunner.getThreadLocalDriver()
                .get()
                .findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(linksText -> linksText.getAttribute("title"))
                .limit(10)
                .collect(Collectors.toList());
    }

    public GoogleHomePage goToHomePageByLogo() {
        return new GoogleHomePage(TestRunner
                .getDriver()
                .findElement(By.xpath("//a[@class='F1hUFe jbTlie']"))
                .getAttribute("href"));
    }
}

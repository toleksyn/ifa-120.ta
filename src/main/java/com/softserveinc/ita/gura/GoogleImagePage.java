package com.softserveinc.ita.gura;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleImagePage {
    public List<String> getImagesTitle() {
        return TestRunner.getDriver()
                .findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"))
                .stream()
                .map(linksText -> linksText.getAttribute("title"))
                .limit(10)
                .collect(Collectors.toList());
    }

    public GoogleHomePage goToHomePageByLogo() {
        TestRunner
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='F1hUFe jbTlie']")))
                .click();
        return new GoogleHomePage();
    }
}

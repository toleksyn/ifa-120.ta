package com.softserveinc.ita.sbendus.pageobject;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.ita.common.WebElementUtil.clickElement;
import static com.softserveinc.ita.common.WebElementUtil.getListOfElements;

public class GoogleSearchImagePage {

    public List<String> getListOfSearchTitleResults() {
//        return TestRunner
//                .getWait()
//                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yuRUbf']/a")))
//                .stream()
//                .map(webElement -> webElement.getAttribute("href"))
//                .collect(Collectors.toList());

        return getListOfElements("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']")
                .stream()
                .map(WebElement -> WebElement.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public GoogleHomePage navigateToHomePageByLogo() {
        clickElement("//a[@class='F1hUFe jbTlie']");
        return new GoogleHomePage();
    }
}



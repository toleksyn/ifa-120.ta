package com.softserveinc.ita.nromanchuk;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ImagesResultsPage {

    public List<WebElement> getListOfTextResults() {
        return TestRunner.getDriver().findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"));
    }

    public GoogleHomePage navigateToGoogleHomePage() {
        TestRunner.getDriver().findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
        return new GoogleHomePage();
    }
}


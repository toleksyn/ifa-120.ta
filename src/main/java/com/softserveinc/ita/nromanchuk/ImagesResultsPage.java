package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ImagesResultsPage {

    public List<WebElement> getListOfTextResults() {
        return TestRunner.driver.findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"));
    }

    public GoogleHomePage goToHomePage() {
        TestRunner.driver.findElement(By.xpath("//a[@class='F1hUFe jbTlie']")).click();
        return new GoogleHomePage();
    }
}


package com.softserveinc.ita.nromanchuk;

import com.softserveinc.ita.common.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BooksResultsPage {

    public List<WebElement> getListOfResultsText() {
        return TestRunner.getDriver().findElements(By.xpath("//h3"));
    }
}

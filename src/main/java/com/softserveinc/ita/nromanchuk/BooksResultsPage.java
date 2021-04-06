package com.softserveinc.ita.nromanchuk;

import org.openqa.selenium.WebElement;

import java.util.List;

public class BooksResultsPage {

    public List<WebElement> getResultsTextList() {
        return WebElementUtil.getElementsList("//h3");
    }

    public String getResultLinkTextByIndex(int index) {
        return WebElementUtil.getElementFromListByIndex("//h3", index).getText().toLowerCase();
    }
}

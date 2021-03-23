package com.softserveinc.ita.vPetrat;

import com.softserveinc.ita.pageobjects_task.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePage {

    private By searchFormLocator = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input");
    private By searchSubmitButton = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]");

    public GoogleHomePage open() {
        TestRunner.getDriver().get("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultPage searchRequest(String request) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(searchFormLocator));
        TestRunner.getDriver().findElement(searchFormLocator).sendKeys(request);
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(searchSubmitButton));
        TestRunner.getDriver().findElement(searchSubmitButton).click();
        return new GoogleSearchResultPage();
    }
}

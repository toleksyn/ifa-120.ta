package com.softserveinc.ita.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WebElementUtil {
    public static void clickElement(String webElementXPath) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(webElementXPath))).click();
    }

    public static void setElementValue(String webElementXPath, String value) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(webElementXPath))).sendKeys(value);
    }

    public static List<WebElement> getElementsList(String webElementsXPath) {
        return TestRunner.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(webElementsXPath)));
    }

    public static WebElement getElementFromListByIndex(String webElementsXPath, int elementIndex) {

        return getElementsList(webElementsXPath).get(elementIndex);

    }

    public static List<WebElement> getElementsListAtLeast(String webElementXPath, int amount) {
        return TestRunner.getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(webElementXPath), amount));
    }

    public static WebElement getElement(String webElementXPath) {
        return TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(webElementXPath)));
    }
}


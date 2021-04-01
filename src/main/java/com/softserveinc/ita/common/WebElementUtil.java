package com.softserveinc.ita.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WebElementUtil {

    public static void clickElement(String webElementXPath) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(webElementXPath))).click();
    }

    public static void setValueForElement(String webElementXPath, String value) {
        TestRunner.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(webElementXPath))).sendKeys(value);
    }

    public static List<WebElement> getListOfElements(String webElementsXPath) {
        return TestRunner.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(webElementsXPath)));
    }

    public static WebElement getElementFromListForIndex(String webElementsXPath, int elementIndex) {
        return getListOfElements(webElementsXPath).get(elementIndex);
    }

    public static List<WebElement> getListOfElementsMoreThenAmount(String webElementXpath, int amount) {
        return TestRunner.getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(webElementXpath), amount));
    }
}
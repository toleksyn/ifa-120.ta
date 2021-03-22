package com.softserveinc.ita.romankhr.pages;

import com.softserveinc.ita.romankhr.framework.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleHomePage extends Page {

    protected RemoteWebDriver driver;
    private String searchFieldLocator = "//input[@class='gLFyf gsfi']";
    private String seachButtonLocator ="//div[1]/div[1]/div[2]/div[2]/div[2]/center[1]/input[1]";

    public GoogleHomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public <T extends Page> T searchTermSearch(Class<T> clazz, String searchTerm) throws Exception {
        driver.findElement(By.xpath(searchFieldLocator)).sendKeys(searchTerm);
        driver.findElement(By.xpath(seachButtonLocator)).click();
        return PageFactory.newPage(driver, clazz);
    }
}

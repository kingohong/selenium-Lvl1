package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {

    private By getTabXPathByName(String tabName) {
        String dynamicXPath = String.format("//a[span[normalize-space()='%s']]", tabName);
        return By.xpath(dynamicXPath);
    }

    public void clickTab(String tabName) {
        WebElement tabElement = DriverManager.getDriver().findElement(getTabXPathByName(tabName));
        tabElement.click();
    }

    public boolean isTabDisplayed(String tabName) {
        return DriverManager.getDriver().findElement(getTabXPathByName(tabName)).isDisplayed();
    }
}

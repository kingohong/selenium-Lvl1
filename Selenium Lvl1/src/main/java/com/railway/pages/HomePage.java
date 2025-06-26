package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {

    public WebElement getWelcomeMessageByEmail(String email) {
        String xpath = String.format("//strong[normalize-space()='Welcome %s']", email);
        return DriverManager.getDriver().findElement(By.xpath(xpath));
    }
}

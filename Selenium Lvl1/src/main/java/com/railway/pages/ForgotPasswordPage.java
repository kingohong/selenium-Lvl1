package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private final By emailAddressTextBox = By.id("email");
    private final By sendInstructionsButton = By.xpath("//input[@value='Send Instructions']");

    public WebElement getEmailAddressTextBox() {
        return DriverManager.getDriver().findElement(emailAddressTextBox);
    }

    public WebElement getSendInstructionsButton() {
        return DriverManager.getDriver().findElement(sendInstructionsButton);
    }
    public void sendEmail(String emailAddress) {
        getEmailAddressTextBox().sendKeys(emailAddress);
        getSendInstructionsButton().click();
    }


}

package com.railway.pages;

import java.util.List;
import java.util.stream.Collectors;

import com.railway.dataobject.Account;
import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("password");
    private final By passwordConfirmTextBox = By.id("confirmPassword");
    private final By pidTextBox = By.id("pid");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By thankYouMessage = By.xpath("//h1[contains(text(), 'Thank you')]");
    private final By errorMessage = By.xpath("//p[contains(@class, 'error')]");
    private final By lengthErrorMessage = By.xpath("//label[@class='validation-error']");

    public WebElement getEmailTextBox() {
        return DriverManager.getDriver().findElement(emailTextBox);
    }

    public WebElement getPasswordTextBox() {
        return DriverManager.getDriver().findElement(passwordTextBox);
    }

    public WebElement getPasswordConfirmTextBox() {
        return DriverManager.getDriver().findElement(passwordConfirmTextBox);
    }

    public WebElement getPidTextBox() {
        return DriverManager.getDriver().findElement(pidTextBox);
    }

    public WebElement getRegisterButton() {
        return DriverManager.getDriver().findElement(registerButton);
    }

    public WebElement getThankYouMessage() {
        return DriverManager.getDriver().findElement(thankYouMessage);
    }

    public String getThankYouMessageText() {
        return getThankYouMessage().getText();
    }

    public String getErrorMessageText() {
        return DriverManager.getDriver().findElement(errorMessage).getText();
    }

    public String getLengthErrorMessageText() {
        return DriverManager.getDriver().findElement(lengthErrorMessage).getText();
    }

    public void register(Account account) {
        getEmailTextBox().sendKeys(account.getUsername());
        getPasswordTextBox().sendKeys(account.getPassword());
        getPasswordConfirmTextBox().sendKeys(account.getConfirmPassword());
        getPidTextBox().sendKeys(account.getPid());
        getRegisterButton().click();
    }

    public List<String> getAllFieldErrorMessages() {
        List<WebElement> errorElements = DriverManager.getDriver().findElements(By.cssSelector("label.validation-error"));
        return errorElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}

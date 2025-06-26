package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By usernameTextBox = By.id("username");
    private final By passwordTextBox = By.id("password");
    private final By loginButton = By.xpath("//input[@value='login']");
    private final By blankErrorMessage = By.xpath("//p[contains(@class, 'LoginForm') and contains(., 'problem with your login')]");
    private final By invalidErrorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.xpath("//a[normalize-space(.)='Forgot Password page']");

    public WebElement getUsernameTextBox() {
        return DriverManager.getDriver().findElement(usernameTextBox);
    }

    public WebElement getPasswordTextBox() {
        return DriverManager.getDriver().findElement(passwordTextBox);
    }

    public WebElement getLoginButton() {
        return DriverManager.getDriver().findElement(loginButton);
    }

    public WebElement getBlankErrorMessage() {
        return DriverManager.getDriver().findElement(blankErrorMessage);
    }

    public String getBlankErrorMessageText() {
        return getBlankErrorMessage().getText();
    }

    public WebElement getInvalidErrorMessage() {
        return DriverManager.getDriver().findElement(invalidErrorMessage);
    }

    public String getInvalidErrorMessageText() {
        return getInvalidErrorMessage().getText();
    }

    public WebElement getForgotPasswordLink() {
        return DriverManager.getDriver().findElement(forgotPasswordLink);
    }

    public boolean isLoginFormDisplayed() {
        return !DriverManager.getDriver().findElements(By.id("username")).isEmpty()
                && !DriverManager.getDriver().findElements(By.id("password")).isEmpty()
                && !DriverManager.getDriver().findElements(By.xpath("//input[@value='login']")).isEmpty();
    }


    public void login(String username, String password) {
        getUsernameTextBox().sendKeys(username);
        getPasswordTextBox().sendKeys(password);
        getLoginButton().click();
    }


}

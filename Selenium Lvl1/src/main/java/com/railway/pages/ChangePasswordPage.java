package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By passwordUpdatedMessage = By.xpath("//p[contains(@class, 'success')]");
    private final By resetTokenTextBox = By.xpath("//input[@id='resetToken']");
    private final By resetPasswordButton = By.xpath("//input[@value='Reset Password']");

    public WebElement getCurrentPasswordTextBox() {
        return DriverManager.getDriver().findElement(currentPasswordTextBox);
    }
    public WebElement getNewPasswordTextBox() {
        return DriverManager.getDriver().findElement(newPasswordTextBox);
    }
    public WebElement getConfirmPasswordTextBox() {
        return DriverManager.getDriver().findElement(confirmPasswordTextBox);
    }
    public WebElement getChangePasswordButton() {
        return DriverManager.getDriver().findElement(changePasswordButton);
    }
    public String getPasswordUpdatedMessageText() {
        return DriverManager.getDriver().findElement(passwordUpdatedMessage).getText();
    }
    public WebElement getResetTokenTextBox() {
        return DriverManager.getDriver().findElement(resetTokenTextBox);
    }
    public WebElement getResetPasswordButton() {
        return DriverManager.getDriver().findElement(resetPasswordButton);
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        getCurrentPasswordTextBox().sendKeys(currentPassword);
        getNewPasswordTextBox().sendKeys(newPassword);
        getConfirmPasswordTextBox().sendKeys(confirmPassword);
        getChangePasswordButton().click();
    }

    public void changePasswordWithCode(String newPassword, String confirmPassword, String code) {
        getNewPasswordTextBox().sendKeys(newPassword);
        getConfirmPasswordTextBox().sendKeys(confirmPassword);
        getResetTokenTextBox().sendKeys(code);
        getResetPasswordButton().click();
    }
}

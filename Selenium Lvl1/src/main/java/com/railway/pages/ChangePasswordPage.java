package com.railway.pages;

import com.railway.dataobject.ChangePassword;
import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePasswordPage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By passwordUpdatedMessage = By.xpath("//p[contains(@class, 'success')]");
    private final By resetTokenTextBox = By.id("resetToken");
    private final By resetPasswordButton = By.xpath("//input[@value='Reset Password']");
    private final By message = By.xpath("//p[contains(@class, 'message')]");
    private final By labelErrorMessage = By.xpath("//label[contains(@class, 'validation-error')]");

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

    public String getMessageText() {
        return DriverManager.getDriver().findElement(message).getText();
    }

    public String getLabelErrorMessageText() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
            WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(labelErrorMessage));
            return label.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public void changePassword(ChangePassword changePassword) {
        getCurrentPasswordTextBox().sendKeys(changePassword.getCurrentPassword());
        getNewPasswordTextBox().sendKeys(changePassword.getNewPassword());
        getConfirmPasswordTextBox().sendKeys(changePassword.getConfirmPassword());
        getChangePasswordButton().click();
    }

    public void changePasswordWithCode(ChangePassword changePassword) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement newPass = wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordTextBox));
        WebElement confirmPass = getConfirmPasswordTextBox();
        WebElement resetToken = getResetTokenTextBox();

        if (changePassword.getNewPassword() != null && !changePassword.getNewPassword().isEmpty()) {
            newPass.clear();
            newPass.sendKeys(changePassword.getNewPassword());
        }

        if (changePassword.getConfirmPassword() != null && !changePassword.getConfirmPassword().isEmpty()) {
            confirmPass.clear();
            confirmPass.sendKeys(changePassword.getConfirmPassword());
        }

        if (changePassword.getToken() != null && !changePassword.getToken().isEmpty()) {
            resetToken.clear();
            resetToken.sendKeys(changePassword.getToken());
        }

        getResetPasswordButton().click();
    }

}

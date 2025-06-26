package com.tests.login;

import com.mailbox.pages.MailPage;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase12 extends TestBase {
    @Test
    public void UserCannotLoginWithUnactivatedAccount() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        MailPage mailPage = new MailPage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        //1. Navigate to QA Railway Login page

        basePage.clickTab("Login");

        //2. Click on "Forgot Password page" link

        loginPage.getForgotPasswordLink().click();

        //3. Enter the email address of the created account in Pre-condition
        //4. Click on "Send Instructions" button

        forgotPasswordPage.sendEmail("testuser@sharklasers.com");

        //5. Open mailbox and click on reset password link

        DriverManager.getDriver().get("https://www.guerrillamail.com/inbox");

        mailPage.adjustEmail("testuser");

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mailPage.clickChangePasswordEmail();

        mailPage.clickResetLink();

        //6. Enter new passwords and remove the Password Reset Token
        //7. Click "Reset Password" button

        changePasswordPage.changePasswordWithCode("123456789", "123456789", "");



        DriverManager.quitDriver();
    }
}

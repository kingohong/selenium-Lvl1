package com.tests.login;

import com.railway.dataobject.ChangePassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utility.Helpers;
import com.railway.utility.MailPage;
import com.tests.base.TestBase;
import com.tests.utilities.ReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase13 extends TestBase {
    private static final Logger logger = LogManager.getLogger(TestCase13.class);

    @Test
    public void ErrorsDisplayIfPasswordAndConfirmPasswordDoNotMatchWhenResettingPassword() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();
        Helpers helpers = new Helpers();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        MailPage mailPage = new MailPage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        logger.info("=== TestCase13: ErrorsDisplayIfPasswordAndConfirmPasswordDoNotMatchWhenResettingPassword ===");
        ReportManager.info("=== TestCase13: ErrorsDisplayIfPasswordAndConfirmPasswordDoNotMatchWhenResettingPassword ===");

        // 1. Navigate to QA Railway Login page
        logger.info("1. Navigate to QA Railway Login page");
        ReportManager.info("1. Navigate to QA Railway Login page");
        basePage.clickTab("Login");

        // 2. Click on "Forgot Password page" link
        logger.info("2. Click on 'Forgot Password page' link");
        ReportManager.info("2. Click on 'Forgot Password page' link");
        loginPage.getForgotPasswordLink().click();

        // 3. Enter the email address of the created account in Pre-condition
        // 4. Click on "Send Instructions" button
        logger.info("3. Enter the email address of the created account in Pre-condition");
        ReportManager.info("3. Enter the email address of the created account in Pre-condition");
        logger.info("4. Click on 'Send Instructions' button");
        ReportManager.info("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendEmail("testuser@sharklasers.com");

        // 5. Open mailbox and click on reset password link
        logger.info("5. Open mailbox and click on reset password link");
        ReportManager.info("5. Open mailbox and click on reset password link");
        DriverManager.getDriver().get("https://www.guerrillamail.com/inbox");

        mailPage.adjustEmail("testuser");

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mailPage.clickChangePasswordEmail();
        mailPage.clickResetLink();

        String originalTab = DriverManager.getDriver().getWindowHandle();
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        for (String tabHandle : DriverManager.getDriver().getWindowHandles()) {
            if (!tabHandle.equals(originalTab)) {
                DriverManager.getDriver().switchTo().window(tabHandle);
                break;
            }
        }

        // 6. Enter different values for password fields
        // 7. Click "Reset Password" button
        logger.info("6. Enter different values for password fields");
        ReportManager.info("6. Enter different values for password fields");
        logger.info("7. Click 'Reset Password' button");
        ReportManager.info("7. Click 'Reset Password' button");

        helpers.scrollToElement(By.xpath("//input[@value='Reset Password']"));
        changePasswordPage.changePasswordWithCode(ChangePassword.DIFFERENT_CONFIRM_PASSWORD);

        // Verify messages
        logger.info("Verify error messages");
        ReportManager.info("Verify error messages");

        String actualMessage = changePasswordPage.getMessageText();
        String expectedMessage = "Could not reset password. Please correct the errors and try again.";

        String actualLabelErrorMessage = changePasswordPage.getLabelErrorMessageText();
        String expectedLabelErrorMessage = "The password confirmation did not match the new password.";

        try {
            Assert.assertEquals(actualMessage, expectedMessage, "Different main error message");
            ReportManager.pass("Main error message matched: " + actualMessage);
        } catch (AssertionError e) {
            ReportManager.fail("Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'");
            throw e;
        }

        try {
            Assert.assertEquals(actualLabelErrorMessage, expectedLabelErrorMessage, "Different label error message");
            ReportManager.pass("Label error message matched: " + actualLabelErrorMessage);
        } catch (AssertionError e) {
            ReportManager.fail("Expected: '" + expectedLabelErrorMessage + "' but got: '" + actualLabelErrorMessage + "'");
            throw e;
        } finally {
            DriverManager.quitDriver();
        }
    }
}

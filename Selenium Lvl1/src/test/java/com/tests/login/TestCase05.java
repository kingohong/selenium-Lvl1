package com.tests.login;

import com.railway.driver.DriverManager;
import com.railway.pages.BasePage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utility.Helpers;
import com.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase05 extends TestBase {
    @Test
    public void SystemShowsMessageWhenUserEntersWrongPasswordSeveralTimes(){
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        Helpers helpers = new Helpers();

        //1. Navigate to QA Railway Website
        //2. Click on "Login" tab
        basePage.clickTab("Login");

        //3. Enter valid information into "Username" textbox except "Password" textbox.
        //4. Click on "Login" button
        helpers.scrollToElement(By.xpath("//input[@value='login']"));

        loginPage.login("kingohong@gmail.com", "");

        //5. Repeat step 3 three more times.

        for (int i = 1; i <= 4; i++) {
            loginPage.login("kingohong@gmail.com", "");
        }

        String actualMessage = loginPage.getInvalidErrorMessageText();

        String expectedMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Message does not match");

        DriverManager.quitDriver();
    }
}

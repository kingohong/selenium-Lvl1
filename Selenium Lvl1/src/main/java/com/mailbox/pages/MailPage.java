package com.mailbox.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailPage {
    private final By usernameBox = By.xpath("//span[@id='inbox-id']");
    private final By domainNameTextBox = By.xpath("//select[@id='gm-host-select']");
    private final By saveButton = By.xpath("//span[@id='inbox-id']//button[text()='Set']");
    private final By usernameTextBox = By.xpath("//span[@id='inbox-id']//input");
    private final By changePasswordEmail = By.xpath("(//tr[td[@class='td2' and contains(., 'thanhletraining03@gmail.com')]])[1]");
    private final By resetLink = By.xpath("//a[contains(@href, 'resetToken=')]");

    public WebElement getUsernameBox() {
        return DriverManager.getDriver().findElement(usernameBox);
    }

    public WebElement getDomainNameTextBox() {
        return DriverManager.getDriver().findElement(domainNameTextBox);
    }

    public WebElement getSaveButton() {
        return DriverManager.getDriver().findElement(saveButton);
    }

    public WebElement getUsernameTextBox() {
        return DriverManager.getDriver().findElement(usernameTextBox);
    }

    public WebElement getChangePasswordEmail() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.elementToBeClickable(changePasswordEmail));
    }

    public WebElement getResetLink() {
        return DriverManager.getDriver().findElement(resetLink);
    }

    public void adjustEmail(String username) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

        getUsernameBox().click();

        wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));

        getUsernameTextBox().sendKeys(username);

        Select select = new Select(getDomainNameTextBox());
        select.selectByVisibleText("sharklasers.com");

        getSaveButton().click();
    }

    public void clickChangePasswordEmail() {
        getChangePasswordEmail().click();
    }

    public void clickResetLink() {
        getResetLink().click();
    }

}

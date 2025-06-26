package com.railway.pages;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyTicketPage {
    private final WebDriver driver = DriverManager.getDriver();

    private final By manageTicketTable = By.xpath("//table[@class='MyTable']//tr[td]");
    private final By cancelButton = By.xpath("//input[@value='Cancel']");

    public WebElement getManageTicketTable() {
        return driver.findElement(manageTicketTable);
    }

    public WebElement getCancelButton() {
        return driver.findElement(cancelButton); // Lấy nút Cancel đầu tiên (nếu không truyền tham số)
    }

    public boolean isManageTableCorrect(String departFrom, String arriveAt, String seatType, String departDate, String amount) {
        WebElement latestRow = driver.findElement(By.xpath("//table[@class='MyTable']//tr[td]"));
        List<WebElement> cells = latestRow.findElements(By.tagName("td"));

        return cells.get(1).getText().equals(departFrom) &&
                cells.get(2).getText().equals(arriveAt) &&
                cells.get(3).getText().equals(seatType) &&
                cells.get(4).getText().equals(departDate) &&
                cells.get(8).getText().equals(amount);
    }

    public void clickCancelButton(String depart, String arrive) {
        String xpath = String.format("//table[@class='MyTable']//tr[td[2][normalize-space(text())='%s'] " + "and td[3][normalize-space(text())='%s']]//input[@value='Cancel']", depart, arrive);

        List<WebElement> buttons = DriverManager.getDriver().findElements(By.xpath(xpath));
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void confirmCancel() {
        driver.switchTo().alert().accept();
    }

    public boolean isTicketCanceled(String depart, String arrive, String seatType, String departDate) {
        List<WebElement> rows = DriverManager.getDriver().findElements(By.xpath("//table[@class='MyTable']//tr[td]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(1).getText().equals(depart) &&
                    cells.get(2).getText().equals(arrive) &&
                    cells.get(3).getText().equals(seatType) &&
                    cells.get(4).getText().equals(departDate)) {
                return true;
            }
        }
        return false;
    }

}

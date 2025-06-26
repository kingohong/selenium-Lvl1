package com.tests.base;

import com.railway.driver.DriverManager;
import org.testng.annotations.*;

public class TestBase {

    @BeforeMethod
    public void beforeTest() {
        DriverManager.createDriver();
        DriverManager.getDriver().get("http://saferailway.somee.com/");
    }

    @AfterMethod
    public void after() {
        DriverManager.quitDriver();
    }
}

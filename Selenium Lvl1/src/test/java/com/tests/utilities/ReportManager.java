package com.tests.utilities;

import com.aventstack.extentreports.ExtentTest;

public class ReportManager {

    public static void info(String message) {
        ExtentTest logger = AppListener.getTestLogger();
        if (logger != null) {
            logger.info(message);
        }
    }

    public static void pass(String message) {
        ExtentTest logger = AppListener.getTestLogger();
        if (logger != null) {
            logger.pass(message);
        }
    }

    public static void fail(String message) {
        ExtentTest logger = AppListener.getTestLogger();
        if (logger != null) {
            logger.fail(message);
        }
    }
}

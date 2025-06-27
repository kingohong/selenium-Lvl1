package com.railway.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/ExtentReport.html");
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("QA Railway Test Results");

            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}

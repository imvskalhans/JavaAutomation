package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        try {
            // Create 'reports' directory if it doesn't exist
            Path reportsDir = Paths.get("reports");
            if (Files.notExists(reportsDir)) {
                Files.createDirectories(reportsDir);
            }

            // Generate timestamped report file
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String reportPath = "reports/ExtentReport_" + timestamp + ".html";

            File reportFile = new File(reportPath);
            reportFile.deleteOnExit(); // Mark for cleanup post-JVM

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Optional: System info for Jenkins context
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Extent Report", e);
        }
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void endReport() {
        extent.flush();
    }
}

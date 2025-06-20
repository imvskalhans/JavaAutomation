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
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String timestampedReportPath;

    static {
        try {
            // Create 'reports' directory if it doesn't exist
            Path reportsDir = Paths.get("reports");
            if (Files.notExists(reportsDir)) {
                Files.createDirectories(reportsDir);
            }

            // Generate timestamped report file path
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            timestampedReportPath = "reports/ExtentReport_" + timestamp + ".html";

            File reportFile = new File(timestampedReportPath);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Optional: Add system info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Extent Report", e);
        }
    }

    // Overloaded: supports name + description
    public static void startTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void endReport() {
        extent.flush();

        // Copy timestamped report to a fixed name
        Path source = Paths.get(timestampedReportPath);
        Path target = Paths.get("reports/ExtentReport.html");
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Failed to copy report to fixed location: " + e.getMessage());
        }
    }
}

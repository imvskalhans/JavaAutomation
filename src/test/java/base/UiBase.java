package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class UiBase {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void cleanUpArtifacts() {
        clearDirectory("screenshots");
        clearDirectory("reports");
        clearFile("ExtentReport.html");
    }

    @BeforeClass
    public void setupDriver() {
        // Ensure path is correct and the file exists
        String driverPath = System.getProperty("user.dir") + "/drivers/msedgedriver.exe";
        File driverFile = new File(driverPath);

        if (!driverFile.exists()) {
            throw new RuntimeException("EdgeDriver not found at: " + driverPath);
        }

        System.setProperty("webdriver.edge.driver", driverPath);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new"); // use new headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--window-size=1920,1080");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setCapability("ms:edgeOptions", options);

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            ExtentReportManager.getTest().fail("Test Failed. Screenshot attached: " + screenshotPath);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.endReport();
    }

    // Utility: Clear all files in a given directory
    public void clearDirectory(String folderName) {
        Path dir = Paths.get(folderName);
        if (Files.exists(dir)) {
            try {
                Files.walk(dir)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                                System.out.println("Deleted file: " + path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete file: " + path + " - " + e.getMessage());
                            }
                        });
            } catch (IOException e) {
                System.err.println("Error cleaning directory '" + folderName + "': " + e.getMessage());
            }
        }
    }

    // Utility: Delete a file if it exists
    public void clearFile(String fileName) {
        Path file = Paths.get(fileName);
        try {
            if (Files.exists(file)) {
                Files.delete(file);
                System.out.println("Deleted file: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Could not delete file: " + fileName + " - " + e.getMessage());
        }
    }


    // Utility: hide ad iframe if present
    protected void removeAdOverlay() {
        try {
            WebElement adIframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", adIframe);
        } catch (NoSuchElementException ignored) {
        }

        try {
            WebElement closeAd = driver.findElement(By.id("close-fixedban"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeAd);
        } catch (NoSuchElementException ignored) {
        }
    }

    // Utility: safe click with JS fallback
    protected void safeClick(By locator) {
        WebElement element = driver.findElement(locator);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(200); // Let page settle
            element.click();
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

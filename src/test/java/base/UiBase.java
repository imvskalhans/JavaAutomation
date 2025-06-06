package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

import java.io.File;

public class UiBase {
    protected WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        // Ensure path is correct and the file exists
        String driverPath = "C:\\Users\\v.bf.singh\\IdeaProjects\\JavaAutomation\\drivers\\msedgedriver.exe";
        File driverFile = new File(driverPath);

        if (!driverFile.exists()) {
            throw new RuntimeException("EdgeDriver not found at: " + driverPath);
        }

        System.setProperty("webdriver.edge.driver", driverPath);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new"); // use new headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--window-size=1920,1080"); //  for full screen
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setCapability("ms:edgeOptions", options);

        driver = new EdgeDriver(options);
        driver.manage().window().maximize(); // Won’t show in headless but still good practice
    }



    @AfterMethod
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            ExtentReportManager.getTest().fail("Test Failed. Screenshot attached: " + screenshotPath);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.endReport();
    }
}

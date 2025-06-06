package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "screenshots";

    public static String takeScreenshot(WebDriver driver, String testName) {
        Path screenshotDir = Paths.get(SCREENSHOT_DIR);
        Path destPath = screenshotDir.resolve(testName + ".png");

        try {
            // Create directory if not exists
            if (Files.notExists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Retry logic to avoid file lock issues
            int attempts = 0;
            while (attempts < 3) {
                try {
                    Files.copy(src.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
                    break;
                } catch (IOException e) {
                    attempts++;
                    Thread.sleep(500); // short delay before retry
                }
            }

            // Mark file to be deleted on JVM exit to avoid Jenkins file locks
            destPath.toFile().deleteOnExit();

            return destPath.toAbsolutePath().toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Screenshot failed";
        }
    }
}
